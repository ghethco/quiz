package com.avires.quiz.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.AnswersDao;
import com.avires.quiz.dao.Question;
import com.avires.quiz.dao.LearningModule;
import com.avires.quiz.dao.LearningModulesDao;

@Service("learningModuleService")
public class LearningModuleService {
	
	private static Logger logger
			= Logger.getLogger(LearningModuleService.class);

	private AnswersDao answersDao;
	private LearningModulesDao learningModuleDao;

	private QuestionsService questionsService;
	private UsersService usersService;

	@Autowired
	public void setLearningModuleDao(LearningModulesDao learningModuleDao) {
		this.learningModuleDao = learningModuleDao;
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

	public void saveOrUpdate(LearningModule learningModule) {
		logger.info("learningModuleService.saveOrUpdate, learningModule= " + learningModule.toString());
		learningModuleDao.saveOrUpdate(learningModule);
	}

	public int create(LearningModule learningModule) {
		logger.info("learningModuleService.create, learningModule = " + learningModule.toString());
		return(learningModuleDao.create(learningModule));
	}
	
	public List<LearningModule> getLearningModules() {
		return learningModuleDao.getLearningModules();
	}

}