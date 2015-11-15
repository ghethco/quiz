package com.avires.quiz.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.avires.quiz.controllers.LoginController;

@Repository
// for translating Hibernate exceptions into Spring exceptions, see
// dao-context.xml
@Transactional
@Component("answersDao")
public class AnswersDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(LoginController.class);

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public int create(Answer answer) {
		logger.info("in AnswersDao.create, answer = " + answer.toString());
		session().save(answer);
		return (answer.getId());
	}
	
	public void saveOrUpdate(Answer answer) {
		logger.info("in AnswersDao.saveOrUpdate, answer = " + answer.toString());
		session().saveOrUpdate(answer);
	}
	
	@SuppressWarnings("unchecked")
	public Answer getAnswer(String username, int question_id) {
        String query_string = "from Answer where username=:username "
        		+ "and question.id=:question_id";
		Query query = session().createQuery(query_string);
		query.setString("username", username);
		query.setInteger("question_id", question_id);
		return (Answer)query.uniqueResult();
	}

	public int resetSource(String username, int source_id) {
		/*
		 * A two stage (sub)query is necessary because MySQL can't handle
		 * a "cross join" in a delete query, see:
		 * http://stackoverflow.com/questions/7246563/
		 * 		hibernate-exception-on-mysql-cross-join-query
		 */
		String query_string = "delete from Answer where user=:user "
				+ "and question.id in"
				+ " (select id from Question where source.id=:source_id)";
		logger.info("query string = [" + query_string + "]");
		Query query = session().createQuery(query_string);
		query.setString("user", username);
		query.setInteger("source_id", source_id);
		return(query.executeUpdate());
	}
}