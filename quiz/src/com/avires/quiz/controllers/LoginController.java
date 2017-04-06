package com.avires.quiz.controllers;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.FormValidationGroup;
import com.avires.quiz.dao.PersistenceValidationGroup;
import com.avires.quiz.dao.Question;
import com.avires.quiz.dao.QuestionStats;
import com.avires.quiz.dao.LearningModule;
import com.avires.quiz.dao.User;
import com.avires.quiz.service.AnswersService;
import com.avires.quiz.service.QandA;
import com.avires.quiz.service.QuestionsService;
import com.avires.quiz.service.LearningModuleService;
import com.avires.quiz.service.UsersService;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	private LearningModuleService learningModuleService;
	private QuestionsService questionsService;
	private AnswersService answersService;
	private UsersService usersService;

	@Autowired
	public void setLearningModuleService(LearningModuleService learningModuleService) {
		this.learningModuleService = learningModuleService;
	}
	
	@Autowired
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@Autowired
	public void setAnswersService(AnswersService answersService) {
		this.answersService = answersService;
	}
	
	@RequestMapping("/")
	public String showRoot(HttpSession session, Locale locale) {
		logger.info("User's locale is: " + locale);
		return("login");
	}

	@RequestMapping("/home")
	public String showHome(Model model, Principal principal) {
		List<QuestionStats> stats
				= questionsService.getStats(principal.getName());
		List<LearningModule> learningModules
				= learningModuleService.getLearningModules();
		logger.info("stats = " + stats);
		logger.info("learningModules = " + learningModules);
		model.addAttribute("question_counts", stats);
		model.addAttribute("learningModules", learningModules);
		return "home";
	}

	/*
	 *  if "select_source" specifies a source, generate a new quiz,
	 *  otherwise, just serve up a new question from the current quiz
	 */
	@RequestMapping("/newquestion")
	public String showQuestion(Model model, Principal principal,
			@RequestParam(value="select_source", required=false) int source_id,
			@RequestParam(value="new_quiz", required=false) boolean new_quiz) {
		QandA qna = questionsService.getQandA(source_id, principal.getName(),
				new_quiz);
		if (qna == null) {
			return "finished";
		}
		model.addAttribute("num_correct",
				qna.getAnswer().getCorrect_count());
		model.addAttribute("num_incorrect",
				qna.getAnswer().getIncorrect_count());
		model.addAttribute("question_obj",
				qna.getQuestion());
		return "newquestion";
	}
	
	@RequestMapping("/submitanswer")			// process student response
	public String submitAnswer(Model model, Principal principal,
			@RequestParam("select_source") int source_id,
			@RequestParam("question_id") int question_id,
			@RequestParam("answer_correct") boolean answer_correct) {

		logger.info("LoginController: submitanswer, question_id = " +
                question_id + " answer_correct = " + answer_correct);

		Answer responses = answersService.getAnswer(principal.getName(),
				question_id);
		answersService.processAnswer(question_id, responses,
				principal.getName(), answer_correct);

		QandA qna = questionsService.getQandA(source_id,
				principal.getName(), false);
		if (qna == null) {
			return "finished";
		}
		
		model.addAttribute("question_obj", qna.getQuestion());
		model.addAttribute("num_correct", qna.getAnswer().getCorrect_count());
		model.addAttribute("num_incorrect",
				qna.getAnswer().getIncorrect_count());
		return "newquestion";
	}
	
	@RequestMapping("/finished")
	public String showFinished() {
		return "finished";
	}

	@RequestMapping("/reset_stats")
	public String resetStats(Model model, Principal principal,
			@RequestParam("source_id") int source_id) {
		answersService.resetSource(principal.getName(), source_id);
		model.addAttribute("source_id", source_id);
		return "reset_stats";
	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/users")
	public String showUsers(Model model) {
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		return "users";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {

		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user,
			BindingResult result, Model model) {

		logger.info("/createaccount entered.");
		
		if (result.hasErrors()) {
    		logger.info("/createaccount: has errors: " + result.toString());
			return "newaccount";
		}

		user.setEnabled(true);

		if (usersService.exists(user.getUsername())) {
			logger.error("Caught duplicate user name in form.");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			logger.error("Caught duplicate user key when saving new user.");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		return "accountcreated";
	}

	@RequestMapping("/editaccount")
	public String editAccount(Model model, @RequestParam("username") String username) {

		model.addAttribute("user", usersService.getUser(username));
		return "editaccount";
	}

	@RequestMapping(value = "/changeaccount", method = RequestMethod.POST)
	public String changeAccount(@RequestParam("company.id") int company_id, Model model,
			@Validated(PersistenceValidationGroup.class) User user, BindingResult result) {

		logger.info("/changeaccount entered.");

		if (result.hasErrors()) {
    		logger.info("/editaccount: has errors: " + result.toString());
			return "editaccount";
		}

		usersService.saveorupdate(user);
		
		return "accountchanged";
	}
	
	@RequestMapping(value = "/deleteaccount")
	public String deleteAccount(@RequestParam("username") String username) {
		User user_to_delete = usersService.getUser(username);
		usersService.delete(user_to_delete);
		return "accountdeleted";
	}

}