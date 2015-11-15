package com.avires.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.avires.quiz.dao.User;
import com.avires.quiz.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) { 
		this.usersDao = usersDao;
	}
	
	public void create(User user) {
		usersDao.create(user);
	}
	
	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	@Secured("ROLE_ADMIN")
	public List<String> getAllUsernames() {
		return usersDao.getAllUsernames();
	}

	@Secured("ROLE_ADMIN")
	public void saveorupdate(User user) {
		usersDao.saveOrUpdate(user);
	}

	@Secured("ROLE_ADMIN")
	public void delete(User user) {
		usersDao.delete(user);
	}

}