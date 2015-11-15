package com.avires.quiz.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.AnswersDao;
import com.avires.quiz.dao.Question;
import com.avires.quiz.dao.Source;
import com.avires.quiz.dao.SourcesDao;

@Service("sourcesService")
public class SourcesService {
	
	private static Logger logger = Logger.getLogger(SourcesService.class);

	private AnswersDao answersDao;
	private SourcesDao sourcesDao;

	private QuestionsService questionsService;
	private UsersService usersService;

	@Autowired
	public void setSourcesDao(SourcesDao sourcesDao) {
		this.sourcesDao = sourcesDao;
	}

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

	public void saveOrUpdate(Source source) {
		logger.info("sourcesService.saveOrUpdate, source = " + source.toString());
		sourcesDao.saveOrUpdate(source);
	}

	public int create(Source source) {
		logger.info("sourcesService.create, source = " + source.toString());
		return(sourcesDao.create(source));
	}
	
	public List<Source> getSources() {
		return sourcesDao.getSources();
	}

}
