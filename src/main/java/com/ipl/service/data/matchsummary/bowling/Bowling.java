package com.ipl.service.data.matchsummary.bowling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bowling {
	private Set<BowlingScore> scores;
	private String title;

	public Set<BowlingScore> getScores() {
		return scores;
	}

	public void setScores(Set<BowlingScore> scores) {
		this.scores = scores;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Bowling{" +
				"scores=" + scores +
				", title='" + title + '\'' +
				"}\n";
	}
}
