package com.avires.quiz.dao;

public class QuestionStats {
    private LearningModule source;
    private int count;
    private int done_count;		/* count of how many questions are "done" */
    private int done_pct;		/* percentage of questions done */
    
    public QuestionStats() {
    }

	public QuestionStats(LearningModule source, int count, int done_count, int done_pct) {
		this.source = source;
		this.count = count;
		this.done_count = done_count;
		this.done_pct = done_pct;
	}

	public LearningModule getSource() {
		return source;
	}

	public void setSource(LearningModule source) {
		this.source = source;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDone_count() {
		return done_count;
	}

	public void setDone_count(int done_count) {
		this.done_count = done_count;
	}

	public int getDone_pct() {
		return done_pct;
	}

	public void setDone_pct(int done_pct) {
		this.done_pct = done_pct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + done_count;
		result = prime * result + done_pct;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		QuestionStats other = (QuestionStats) obj;
		if (count != other.count)
			return false;
		if (done_count != other.done_count)
			return false;
		if (done_pct != other.done_pct)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionStats [source=" + source + ", count=" + count
				+ ", done_count=" + done_count + ", done_pct=" + done_pct + "]";
	}
    
}