package com.ipl.service.dto.matchsummary.batting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Batting {
	private Set<BattingScore> scores;
	private String title;

	public Set<BattingScore> getScores() {
		return scores;
	}

	public void setScores(Set<BattingScore> scores) {
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
		return "Batting{" +
				"scores=" + scores +
				", title='" + title + '\'' +
				"}\n";
	}
}
