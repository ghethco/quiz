package com.avires.quiz.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.AnswersDao;
import com.avires.quiz.dao.Question;

@Service("answersService")
public class AnswersService {
	
	private static Logger logger = Logger.getLogger(AnswersService.class);
	private AnswersDao answersDao;
	private QuestionsService questionsService;
	private UsersService usersService;

	@Autowired
	public void setAnswersDao(AnswersDao answersDao) {
		this.answersDao = answersDao;
	}

	@Autowired
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void saveOrUpdate(Answer answer) {
		logger.info("answersService.saveOrUpdate, answer = " + answer.toString());
		answersDao.saveOrUpdate(answer);
	}

	public int create(Answer answer) {
		logger.info("answersService.create, answer = " + answer.toString());
		return(answersDao.create(answer));
	}

	public Answer getAnswer(String username, int question_id) {
        Question question = questionsService.getQuestion(question_id);
		Answer answer = answersDao.getAnswer(username, question_id);
		if (answer == null) {
			/* means user has not answered this question yet */
            answer = new Answer(usersService.getUser(username),
                            question, new Date(), 0, 0);
        }
		return(answer);
	}
	
	public void processAnswer(int question_id, Answer answer,
			String username, boolean answer_correct) {
		logger.info("processAnswer: question_id = " + question_id +
				"answer_correct = " + answer_correct);
		if (answer != null) {
			logger.info("answer != null");
			answer.setAnswer_time(new Date());
            if (answer_correct) {
                answer.setCorrect_count
                		(answer.getCorrect_count() + 1);
            } else {
                answer.setIncorrect_count
                		(answer.getIncorrect_count() + 1);
            }
		} else {
			logger.info("answer == null");
            if (answer_correct) {
                answer = new Answer(usersService.getUser(username),
                        questionsService.getQuestion(question_id),
                        new Date(), 1, 0);
            } else {
                answer = new Answer(usersService.getUser(username),
                        questionsService.getQuestion(question_id),
                        new Date(), 0, 1);
            }
		}
		this.saveOrUpdate(answer);
	}

	public void resetSource(String username, int source_id) {
		int status = answersDao.resetSource(username, source_id);
		logger.info("resetSource(" + username + ", " + source_id
				+ ") returned: " + status);
	}
}
