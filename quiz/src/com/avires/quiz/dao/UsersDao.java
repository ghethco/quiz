package com.avires.quiz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository			// for translating Hibernate exceptions into Spring exceptions, see dao-context.xml
@Transactional
@Component("usersDao")
public class UsersDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("in usersDao.create, user = " + user.toString());
		session().save(user);
	}
	
	@Transactional
	public void saveOrUpdate(User user) {
		session().saveOrUpdate(user);
	}
	
	public void delete(User user) {
		/*
		 * ToDo: also delete dependent entries in ALL other tables
		 * -or- just deactivate user
		 * 
		 * For now, do nothing
		 */
//		session().delete(user);
	}
	
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		return (User)crit.uniqueResult();
	}

	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();  // "User" is bean name, not table name
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllUsernames() {
		return session().createQuery("select u.username from User u").list();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getGroupIds(String username) {
		Query query = session().createQuery(
				"select group_id from UsersToGroups where username=:username");
		query.setString("username", username);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<String> getUsernamesInGroup(Integer group_id) {
		Query query = session().createQuery(
				"select username from UsersToGroups where group_id=:group_id");
		query.setInteger("group_id", group_id);
		return query.list();
	}

//	public List<String> getGroups(String username) {
//		return session().createQuery("select * from groups ").list();
//	}
}
