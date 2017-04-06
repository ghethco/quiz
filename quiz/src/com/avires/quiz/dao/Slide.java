package com.avires.quiz.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="slides")
public class Slide {
	
	@Id
	@GeneratedValue
	private int id;
	private String slide_type;
	private String slide_title;
	private String slide_subtitle;
	
	@ManyToOne
	@JoinColumn(name="learning_modules_id")
	private LearningModule learningModule;
	
	@ManyToOne
	@JoinColumn(name="learning_modules_courses_id")
	private Course course;

	public Slide(String slide_type, String slide_title, String slide_subtitle,
			Course course) {
		this.slide_type = slide_type;
		this.slide_title = slide_title;
		this.slide_subtitle = slide_subtitle;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlide_type() {
		return slide_type;
	}

	public void setSlide_type(String slide_type) {
		this.slide_type = slide_type;
	}

	public String getSlide_title() {
		return slide_title;
	}

	public void setSlide_title(String slide_title) {
		this.slide_title = slide_title;
	}

	public String getSlide_subtitle() {
		return slide_subtitle;
	}

	public void setSlide_subtitle(String slide_subtitle) {
		this.slide_subtitle = slide_subtitle;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((slide_subtitle == null) ? 0 : slide_subtitle.hashCode());
		result = prime * result
				+ ((slide_title == null) ? 0 : slide_title.hashCode());
		result = prime * result + ((slide_type == null) ? 0 : slide_type.hashCode());
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
		Slide other = (Slide) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (slide_subtitle == null) {
			if (other.slide_subtitle != null)
				return false;
		} else if (!slide_subtitle.equals(other.slide_subtitle))
			return false;
		if (slide_title == null) {
			if (other.slide_title != null)
				return false;
		} else if (!slide_title.equals(other.slide_title))
			return false;
		if (slide_type == null) {
			if (other.slide_type != null)
				return false;
		} else if (!slide_type.equals(other.slide_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", slide_type=" + slide_type + ", slide_title="
				+ slide_title + ", slide_subtitle=" + slide_subtitle + ", course="
				+ course + "]";
	}

}
