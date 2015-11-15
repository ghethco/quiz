package com.avires.quiz.service;

import com.avires.quiz.dao.Answer;
import com.avires.quiz.dao.Question;

public class QandA {
	private Question _question;
	private Answer _answer;

	public QandA(Question question, Answer answer) {
		_question = question;
		_answer = answer;
	}

	public Question getQuestion() {
		return _question;
	}

	public Answer getAnswer() {
		return _answer;
	}

	public void setQuestion(Question question) {
		_question = question;
	}

	public void setAnswer(Answer answer) {
		_answer = answer;
	}
}