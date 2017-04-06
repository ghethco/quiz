package com.avires.quiz.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.validator.Var;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.Question;
import com.avires.quiz.dao.QuestionStats;
import com.avires.quiz.dao.QuestionsDao;
import com.avires.quiz.dao.LearningModule;

@Service("questionsService")
public class QuestionsService {
	
	private static Logger logger = Logger.getLogger(QuestionsService.class);

	private UsersService usersService;
	private AnswersService answersService;
	private LearningModuleService learningModuleService;

	private QuestionsDao questionsDao;

	private static List<QandA> current_quiz;
	
	private static final int num_questions = 10;
	private static final int max_tries = 500;
	private int _last_question_asked = 0;

	@Autowired
	public void setQuestionsDao(QuestionsDao questionsDao) {
		this.questionsDao = questionsDao;
	}

	@Autowired
	public void setLearningModuleService(LearningModuleService learningModuleService) {
		this.learningModuleService = learningModuleService;
	}
	
	@Autowired
	public void setAnswersService(AnswersService answersService) {
		this.answersService = answersService;
	}
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public List<QuestionStats> getStats(String username) {
		List<LearningModule> all_learningModules
				= learningModuleService.getLearningModules();
		logger.info("all_learningModules = " + all_learningModules);
		List<QuestionStats> lm_counts = new ArrayList<QuestionStats>();
		double numQuestions, numGood, pctDone = 0;
		for (LearningModule lm: all_learningModules) {
			numQuestions = this.getNumQuestions(lm);
			numGood = this.getNumGood(lm, username);
			if (numQuestions > 0) {
                pctDone = (numQuestions - numGood) / numQuestions * 100;
			} else {
                pctDone = 0;
			}
			logger.info("getStats: numQuestions = " + numQuestions
					+ " numGood = " + numGood
					+ " pctDone = " + pctDone);
			int nQ = (int)Math.round(numQuestions);
			int nG = (int)Math.round(numGood);
			int pD = (int)Math.round(pctDone);
			QuestionStats qstats = new QuestionStats(lm, nQ, nQ - nG, pD);
			lm_counts.add(qstats);
		}
		return (lm_counts);
	}

	
	public int getNumQuestions(LearningModule learningModule) {
		int nquestions = (int)questionsDao.getNumQuestions(learningModule);
		return (nquestions);
	}
	
	public int getNumGood(LearningModule learningModule, String username) {
		return(this.getAllGood(learningModule.getId(), username).size());
	}
	
	public List<Question> getQuestions(int learningModule_id) {
		return(questionsDao.getQuestions(learningModule_id));
	}
	
	public Question getRandomQuestion(List<Question> questions) {
        logger.info("getRandomQuestion: questions.size = " + questions.size());
        int index = new Random().nextInt(questions.size());
        logger.info("getRandomQuestion: index = " + index);
        return(questions.get(index));
	}
	
	public List<QandA> getAllGood(int learningModule_id, String username) {
		List<Question> all_learningModules = this.getQuestions(learningModule_id);
		List<QandA> all_good = new ArrayList<QandA>();
		for (Question qu : all_learningModules) {
			Answer ans = answersService.getAnswer(username, qu.getId());
			QandA qna = new QandA(qu, ans);
			if (this.isGoodQuestion(qna)) {
				all_good.add(qna);
			}
		}
		return(all_good);
	}

	/*
	 * This is the normal call from the client, requesting a question
	 */
	public QandA getQandA(int learningModule_id, String username, boolean new_quiz) {
		int status = 0;
		QandA qna = null;
		if (current_quiz == null || current_quiz.size() == 0 || new_quiz) {
			status = this.newQuiz(learningModule_id, num_questions, username);
			logger.info("newQuiz returns: " + status);
		}
		if (status == 0) {
            qna = this.newQuizQuestion(username);
		}
		if (qna != null) {
            _last_question_asked = qna.getQuestion().getId();
		}
        return(qna);
	}

	public int newQuiz(int learningModule_id, int nQuestions, String username) {
		logger.info("newQuiz: learningModule_id = " + learningModule_id + " nQuestions = " +
				nQuestions + " username = " + username);
		current_quiz = new ArrayList<QandA>();
		int num_tries = 0;
		boolean have_quiz = false;
		while (!have_quiz) {
			List<Question> questions = this.getQuestions(learningModule_id);
            Question question = this.getRandomQuestion(questions);
            logger.info("question = " + question.toString());
            Answer answer =
                answersService.getAnswer(username, question.getId());
            logger.info("answer = " + answer.toString());
            QandA qna = new QandA(question, answer);
            logger.info("newQuiz, qna = " + qna.toString());
            if (isGoodQuestion(qna) &&
            		!this.qInQuiz(qna.getQuestion().getId())) {
                current_quiz.add(qna);
            }
            if (++num_tries > max_tries) {
            	current_quiz = this.getAllGood(learningModule_id, username);
            	if (current_quiz.size() == 0) {
                    return(1);	/* could not build a quiz */
            	}
            	have_quiz = true;
            } else if (current_quiz.size() == num_questions) {
            	have_quiz = true;
            }
		}
        logger.info("CURRENT QUIZ SIZE: " + current_quiz.size());
        for (QandA qna : current_quiz) {
            logger.info("CURRENT QUIZ: " +
                            qna.getQuestion().getId() + ": " +
                            qna.getQuestion().getQuestion());
        }
		return(0);	/* normal */
	}
	
	private boolean qInQuiz(int id) {
		for (QandA qna : current_quiz) {
			if (qna.getQuestion().getId() == id) {
				return true;
			}
		}
		return false;
	}

	private QandA getRandomQuizQuestion(String username) {
		QandA qna = null;
		logger.info("getRandomQuizQuestion: _last_question_asked = " +
				_last_question_asked);
		int index = 0;
		int question_id = 0;
		int n_questions = current_quiz.size();
		do {
            index = new Random().nextInt(n_questions);
            logger.info("getRandomQuizQuestion, current_quiz.size() = " +
                            n_questions + " index = " + index);
            qna = current_quiz.get(index);
            question_id = qna.getQuestion().getId();
        } while (question_id == _last_question_asked && n_questions != 1);
        /*
         * Refresh the answer correct and incorrect counts
         * from the database
         */
        Answer answer = answersService.getAnswer(username,
        		qna.getQuestion().getId());
        qna.setAnswer(answer);
        return(qna);
	}
	
	private boolean isGoodQuestion(QandA question) {
        Date answer_time = question.getAnswer().getAnswer_time();
        // logger.info("answer_time(Date) = " + answer_time.toString());
        // logger.info("answer_time(Instant) = " + answer_time.toInstant());
        Date tnow = new Date();
        // logger.info("tnow(string) = " + tnow.toString());
        // logger.info("tnow(Instant) = " + tnow.toInstant());
        /*
         * The deprecated java.util.Date class is used because
         * it is compatible with Hibernate.  The new Java8 date/time
         * classes "java.time" are not (yet).  So, we convert "Date"
         * to "LocalDateTime" for comparison since java.time class
         * has better utilties for that.
         */
        Instant instant = Instant.ofEpochMilli(answer_time.getTime());
        // logger.info("answer_time(InstantEpoch) = " + instant.toString());
        LocalDateTime ans_time = LocalDateTime.ofInstant(instant,
                        ZoneId.systemDefault());
        // logger.info("ans_time = " + ans_time.toString());
        LocalDateTime time_now = LocalDateTime.now();
        // logger.info("time_now = " + time_now.toString());
        long diffInMinutes =
                java.time.Duration.between(ans_time, time_now).toMinutes();
        int nright = question.getAnswer().getCorrect_count();
        int nwrong = question.getAnswer().getIncorrect_count();
        if (nright > 0 || nwrong > 0) {
            logger.info("nright = " + nright);
            logger.info("nwrong = " + nwrong);
            logger.info("diffInMinutes = " + diffInMinutes);
            if (nright < 3 || nright < nwrong + 3) {
                logger.info("Question is good.");
            } else {
                logger.info("Question is not good.");
            }
        }
        /*
         * If any of the following are true, the question is "good",
         * which means that we can ask it:
         * 		o It has not been answered three times correctly
         * 		o It has not been answered correctly three times
         * 		  more than it has been answered incorrectly
         * 		o It has not been answered in the last 48 hours
         */
        if (nright < 3 || nright < nwrong + 3 || diffInMinutes > 69120) {
        	// logger.info("Question is good.");
            return(true);
        } else {
        	// logger.info("Question is not good.");
        	return(false);
        }
	}
	
	/*
	 * Select a random question from "current_quiz", taking into account
	 * the user's previous correct and incorrect responses
	 */
	public QandA newQuizQuestion(String username) {
		boolean good_question = false;
		QandA qna = null;
        int num_tries = 0;
		while (!good_question) {
            qna = this.getRandomQuizQuestion(username);
            good_question = this.isGoodQuestion(qna);
            logger.info("newQuestion, good = " + good_question
            		+ " " + qna.getQuestion().toString());
            if (qna.getQuestion().getType().contains("SA")
            		|| qna.getQuestion().getType().contains("TF")) {
                logger.info("Answer1 = " + qna.getQuestion().getAnswer1()
                        + " Answer2 = " + qna.getQuestion().getAnswer2()
                        + " Answer3 = " + qna.getQuestion().getAnswer3());
            } else if (qna.getQuestion().getType().contains("MC")) {
            	logger.info("Answer = " + qna.getQuestion().getCorrect());
            }
            if (++num_tries > max_tries) {
            	return(null);
            }
		}
		return qna;
	}
	
	public void newQuizByType(int learningModule_id, String type, int nQuestions) {
		current_quiz = new ArrayList<QandA>();
		for (int i = 0; i < nQuestions; i++) {
			Question q = questionsDao.randomQuestion(learningModule_id);
		}
		return;
	}

	public Question getQuestion() {
		return questionsDao.getQuestion();
	}
	
	public Question getQuestion(int id) {
		return questionsDao.getQuestion(id);
	}
	
	public List<Question> getAllQuestions() {
		return questionsDao.getAllQuestions();
	}

	public int getQuestionCount() {
		logger.info("");
		return questionsDao.getQuestionCount();
	}
	
	public List<Question> getQuiz() {
		return questionsDao.getQuiz();
	}
}
