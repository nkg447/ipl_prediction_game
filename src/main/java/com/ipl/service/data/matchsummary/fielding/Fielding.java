package com.ipl.service.data.matchsummary.fielding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fielding {
	private String title;
	private Set<FieldingScore> scores;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<FieldingScore> getScores() {
		return scores;
	}

	public void setScores(Set<FieldingScore> fieldingScores) {
		this.scores = fieldingScores;
	}

}
