package com.avires.quiz.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository		// for translating Hibernate exceptions into Spring exceptions
@Transactional	// see dao-context.xml
@Component("coursesDao")
public class CoursesDao {
	
	private static Logger logger = Logger.getLogger(CoursesDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public Course getCourse(int id) {
		Criteria crit = session().createCriteria(Course.class);
		crit.add(Restrictions.idEq(id));
		return (Course)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Course> getAllCourses() {
		Criteria crit = session().createCriteria(Course.class);
		return crit.list();
	}

}
