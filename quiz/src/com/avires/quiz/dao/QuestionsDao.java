package com.avires.quiz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository		// for translating Hibernate exceptions into Spring exceptions
@Transactional	// see dao-context.xml
@Component("questionsDao")
public class QuestionsDao {
	
	private static Logger logger = Logger.getLogger(QuestionsDao.class);
	private static List<Question> current_quiz;

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public Question getQuestion(int id) {
		Criteria crit = session().createCriteria(Question.class);
		crit.add(Restrictions.idEq(id));
		return (Question)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestions() {
		Criteria crit = session().createCriteria(Question.class);
		return crit.list();
	}

	public int getQuestionCount() {
		Criteria crit = session().createCriteria(Question.class);
		return crit.list().size();
	}
	
	public Question getQuestion() {
		int nQuestions = current_quiz.size();
		Random rn = new Random();
		int index = rn.nextInt(nQuestions);
		return(current_quiz.get(index));
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions(int source_id) {
		/*  UNTESTED
		Query query = session().createQuery
				("from Question where source.id=:qsource and type like :qtype");
		query.setInteger("qsource", source_id);

		// for testing - return only one type

		query.setString("qtype", "SA%");

		return(query.list());
		*/
		
		Criteria crit = session().createCriteria(Question.class);
		crit.createAlias("source", "s");
		crit.add(Restrictions.eq("s.id", source_id));
		return(crit.list());
	}
	
	public List<Question> getQuiz() {
		return current_quiz;
	}
    /*
     * Generate a random question from source "source_name" in the database
     */
	@SuppressWarnings("unchecked")
	public Question randomQuestion(int source_id) {
		List<Question> questions_from_source = new ArrayList<Question>(); 
		logger.info("newQuizQuestion, source_id = [" + source_id + "]");
		
		Criteria crit = session().createCriteria(Question.class);
		crit.add(Restrictions.idEq(source_id));
		questions_from_source = crit.list();
        int index = new Random().nextInt(questions_from_source.size());
		// logger.info("Result = " + result.toString());
		return questions_from_source.get(index);
	}
	
	public void newQuizByType(String source, String type, int nQuestions) {
		current_quiz = new ArrayList<Question>();
		Query query = session().createQuery
				("from Question where source=:qsource and type=:qtype");
		query.setString("qsource", source);
		query.setString("qtype", type);
		int num_qs = 0;
		for (Object obj: query.list()) {
			current_quiz.add((Question)obj);
			if (++num_qs >= nQuestions) {
				break;
			}
		}
		return;
	}

	public long getNumQuestions(Source source) {

		Criteria crit = session().createCriteria(Question.class);
		crit.createAlias("source", "s");
		crit.add(Restrictions.eq("s.id", source.getId()));
		crit.setProjection(Projections.rowCount());

		return (long)crit.uniqueResult();
	}
	
}