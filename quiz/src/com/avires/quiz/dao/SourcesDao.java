package com.avires.quiz.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.avires.quiz.controllers.LoginController;

@Repository
// for translating Hibernate exceptions into Spring exceptions, see
// dao-context.xml
@Transactional
@Component("sourcesDao")
public class SourcesDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(LoginController.class);

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public int create(Source source) {
		logger.info("in SourcesDao.create, source = " + source.toString());
		session().save(source);
		return (source.getId());
	}
	
	public void saveOrUpdate(Source source) {
		logger.info("in SourcesDao.saveOrUpdate, source = " + source.toString());
		session().saveOrUpdate(source);
	}
	
	public List<Source> getSources() {
		Criteria crit = session().createCriteria(Source.class);
		return (crit.list());
	}

	@SuppressWarnings("unchecked")
	public Source getSource(int question_id) {

        String query_string = "from Source question.id=:question_id";
		Query query = session().createQuery(query_string);
		query.setInteger("question_id", question_id);

		return (Source)query.uniqueResult();
	}
	
	public Source getSource(Question question) {

		Criteria crit = session().createCriteria(Question.class);
		crit.createAlias("question", "q");
		crit.add(Restrictions.eq("q.id", question.getId()));

		return (Source)crit.uniqueResult();
	}

	public int resetSource(String username, String source) {
		/*
		 * A two stage (sub)query is necessary because MySQL can't handle
		 * a "cross join" in a delete query, see:
		 * http://stackoverflow.com/questions/7246563/
		 * 		hibernate-exception-on-mysql-cross-join-query
		 */
		String query_string = "delete from Source where user=:user "
				+ "and question.id in"
				+ " (select id from Question where source=:source)";
		Query query = session().createQuery(query_string);
		query.setString("user", username);
		query.setString("source", source);
		return(query.executeUpdate());
	}
}