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
@Component("slidelinksDao")
public class SlideLinksDao {
	
	private static Logger logger = Logger.getLogger(SlideLinksDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public SlideLink getSlideLink(int id) {
		Criteria crit = session().createCriteria(SlideLink.class);
		crit.add(Restrictions.idEq(id));
		return (SlideLink)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<SlideLink> getAllSlideLinks() {
		Criteria crit = session().createCriteria(SlideLink.class);
		return crit.list();
	}

}
