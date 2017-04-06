package com.avires.quiz.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="slide_links")
public class SlideLink {
	
	@Id
	@GeneratedValue
	private int id;
	private String link_text;
	private String link_url;
	
	@ManyToOne
	@JoinColumn(name="slides_id")
	private Slide slide;
	
	@ManyToOne
	@JoinColumn(name="slides_learning_modules_id")
	private LearningModule learningModule;
	
	@ManyToOne
	@JoinColumn(name="learning_modules_courses_id")
	private Course course;

	public SlideLink(String link_text, String link_url, Slide slide,
			LearningModule learningModule, Course course) {
		this.link_text = link_text;
		this.link_url = link_url;
		this.slide = slide;
		this.learningModule = learningModule;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink_text() {
		return link_text;
	}

	public void setLink_text(String link_text) {
		this.link_text = link_text;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public Slide getSlide() {
		return slide;
	}

	public void setSlide(Slide slide) {
		this.slide = slide;
	}

	public LearningModule getLearningModule() {
		return learningModule;
	}

	public void setLearningModule(LearningModule learningModule) {
		this.learningModule = learningModule;
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
				+ ((learningModule == null) ? 0 : learningModule.hashCode());
		result = prime * result + ((link_text == null) ? 0 : link_text.hashCode());
		result = prime * result + ((link_url == null) ? 0 : link_url.hashCode());
		result = prime * result + ((slide == null) ? 0 : slide.hashCode());
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
		SlideLink other = (SlideLink) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (learningModule == null) {
			if (other.learningModule != null)
				return false;
		} else if (!learningModule.equals(other.learningModule))
			return false;
		if (link_text == null) {
			if (other.link_text != null)
				return false;
		} else if (!link_text.equals(other.link_text))
			return false;
		if (link_url == null) {
			if (other.link_url != null)
				return false;
		} else if (!link_url.equals(other.link_url))
			return false;
		if (slide == null) {
			if (other.slide != null)
				return false;
		} else if (!slide.equals(other.slide))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SlideLink [id=" + id + ", link_text=" + link_text + ", link_url="
				+ link_url + ", slide=" + slide + ", learningModule="
				+ learningModule + ", course=" + course + "]";
	}

}
