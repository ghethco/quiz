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
@Component("slidesDao")
public class SlidesDao {
	private static Logger logger = Logger.getLogger(SlidesDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public Slide getSlide(int id) {
		Criteria crit = session().createCriteria(Slide.class);
		crit.add(Restrictions.idEq(id));
		return (Slide)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Slide> getAllSlides() {
		Criteria crit = session().createCriteria(Slide.class);
		return crit.list();
	}


}
