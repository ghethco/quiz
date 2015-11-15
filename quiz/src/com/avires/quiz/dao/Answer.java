package com.avires.quiz.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

@Entity
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="questions_id")
	private Question question;

	@Temporal(TemporalType.TIMESTAMP)
	private Date answer_time;
	
	private int correct_count;
	private int incorrect_count;

	private static Logger logger = Logger.getLogger(Answer.class);

	public Answer() {
		
	}

	public Answer(User user, Question question, Date answer_time,
			int correct_count, int incorrect_count) {
		this.user = user;
		this.question = question;
		this.answer_time = answer_time;
		this.correct_count = correct_count;
		this.incorrect_count = incorrect_count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Date getAnswer_time() {
		return answer_time;
	}

	public void setAnswer_time(Date answer_time) {
		this.answer_time = answer_time;
	}

	public int getCorrect_count() {
		return correct_count;
	}

	public void setCorrect_count(int correct_count) {
		this.correct_count = correct_count;
	}

	public int getIncorrect_count() {
		return incorrect_count;
	}

	public void setIncorrect_count(int incorrect_count) {
		this.incorrect_count = incorrect_count;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Answer.logger = logger;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((answer_time == null) ? 0 : answer_time.hashCode());
		result = prime * result + correct_count;
		result = prime * result + id;
		result = prime * result + incorrect_count;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answer_time == null) {
			if (other.answer_time != null)
				return false;
		} else if (!answer_time.equals(other.answer_time))
			return false;
		if (correct_count != other.correct_count)
			return false;
		if (id != other.id)
			return false;
		if (incorrect_count != other.incorrect_count)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", user=" + user + ", question=" + question
				+ ", answer_time=" + answer_time + ", correct_count="
				+ correct_count + ", incorrect_count=" + incorrect_count + "]";
	}

}