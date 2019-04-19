package com.ipl.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.log4j.Logger;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetail {
	final static Logger logger = Logger.getLogger(MatchDetail.class);
	private List<Team> squad;

	public List<Team> getSquad() {
		return squad;
	}

	public void setSquad(List<Team> squad) {
		this.squad = squad;
	}

	@Override
	public String toString() {
		return "MatchDetail{" +
				"squad=" + squad +
				"}\n";
	}
}
