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
@Component("learningmoduleDao")
public class LearningModulesDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(LoginController.class);

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public int create(LearningModule learningModule) {
		logger.info("in LearningModuleDao.create, learningModule = " + learningModule.toString());
		session().save(learningModule);
		return (learningModule.getId());
	}
	
	public void saveOrUpdate(LearningModule learningModule) {
		logger.info("in LearningModuleDao.saveOrUpdate, learningModule = " + learningModule.toString());
		session().saveOrUpdate(learningModule);
	}
	
	public List<LearningModule> getLearningModules() {
		Criteria crit = session().createCriteria(LearningModule.class);
		return (crit.list());
	}

	@SuppressWarnings("unchecked")
	public LearningModule getLearningModule(int question_id) {

        String query_string = "from LearningModule question.id=:question_id";
		Query query = session().createQuery(query_string);
		query.setInteger("question_id", question_id);

		return (LearningModule)query.uniqueResult();
	}
	
	public LearningModule getLearningModule(Question question) {

		Criteria crit = session().createCriteria(Question.class);
		crit.createAlias("question", "q");
		crit.add(Restrictions.eq("q.id", question.getId()));

		return (LearningModule)crit.uniqueResult();
	}

	public int resetLearningModule(String username, String learningModule) {
		/*
		 * A two stage (sub)query is necessary because MySQL can't handle
		 * a "cross join" in a delete query, see:
		 * http://stackoverflow.com/questions/7246563/
		 * 		hibernate-exception-on-mysql-cross-join-query
		 */
		String query_string = "delete from LearningModule where user=:user "
				+ "and question.id in"
				+ " (select id from Question where learningModule=:learningModule)";
		Query query = session().createQuery(query_string);
		query.setString("user", username);
		query.setString("learningModule", learningModule);
		return(query.executeUpdate());
	}
}