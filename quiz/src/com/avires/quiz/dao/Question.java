package com.avires.quiz.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
	
	@Id
	@GeneratedValue
	private int id;
	private int faa_num;
	private String correct;
	private String question;
	private String before_image;
	private String after_image;
	private String type;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	
	@ManyToOne
	@JoinColumn(name="sources_id")
	private Source source;
	
	public Question() {
	}

	public Question(int faa_num, String correct, String question,
			String before_image, String after_image, String type,
			String answer1, String answer2, String answer3, String answer4,
			String answer5, String answer6, Source source) {
		super();
		this.faa_num = faa_num;
		this.correct = correct;
		this.question = question;
		this.before_image = before_image;
		this.after_image = after_image;
		this.type = type;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
		this.answer6 = answer6;
		this.source = source;
	}

	public int getFaa_num() {
		return faa_num;
	}

	public void setFaa_num(int faa_num) {
		this.faa_num = faa_num;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getBefore_image() {
		return before_image;
	}

	public void setBefore_image(String before_image) {
		this.before_image = before_image;
	}

	public String getAfter_image() {
		return after_image;
	}

	public void setAfter_image(String after_image) {
		this.after_image = after_image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public String getAnswer6() {
		return answer6;
	}

	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((after_image == null) ? 0 : after_image.hashCode());
		result = prime * result + ((answer1 == null) ? 0 : answer1.hashCode());
		result = prime * result + ((answer2 == null) ? 0 : answer2.hashCode());
		result = prime * result + ((answer3 == null) ? 0 : answer3.hashCode());
		result = prime * result + ((answer4 == null) ? 0 : answer4.hashCode());
		result = prime * result + ((answer5 == null) ? 0 : answer5.hashCode());
		result = prime * result + ((answer6 == null) ? 0 : answer6.hashCode());
		result = prime * result
				+ ((before_image == null) ? 0 : before_image.hashCode());
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result + faa_num;
		result = prime * result + id;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Question other = (Question) obj;
		if (after_image == null) {
			if (other.after_image != null)
				return false;
		} else if (!after_image.equals(other.after_image))
			return false;
		if (answer1 == null) {
			if (other.answer1 != null)
				return false;
		} else if (!answer1.equals(other.answer1))
			return false;
		if (answer2 == null) {
			if (other.answer2 != null)
				return false;
		} else if (!answer2.equals(other.answer2))
			return false;
		if (answer3 == null) {
			if (other.answer3 != null)
				return false;
		} else if (!answer3.equals(other.answer3))
			return false;
		if (answer4 == null) {
			if (other.answer4 != null)
				return false;
		} else if (!answer4.equals(other.answer4))
			return false;
		if (answer5 == null) {
			if (other.answer5 != null)
				return false;
		} else if (!answer5.equals(other.answer5))
			return false;
		if (answer6 == null) {
			if (other.answer6 != null)
				return false;
		} else if (!answer6.equals(other.answer6))
			return false;
		if (before_image == null) {
			if (other.before_image != null)
				return false;
		} else if (!before_image.equals(other.before_image))
			return false;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (faa_num != other.faa_num)
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", faa_num=" + faa_num + ", correct="
				+ correct + ", question=" + question + ", before_image="
				+ before_image + ", after_image=" + after_image + ", type="
				+ type + ", answer1=" + answer1 + ", answer2=" + answer2
				+ ", answer3=" + answer3 + ", answer4=" + answer4
				+ ", answer5=" + answer5 + ", answer6=" + answer6 + ", source="
				+ source + "]";
	}
	
}