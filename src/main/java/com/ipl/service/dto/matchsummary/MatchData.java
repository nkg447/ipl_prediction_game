package com.ipl.service.dto.matchsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ipl.service.dto.Team;
import com.ipl.service.dto.matchsummary.batting.Batting;
import com.ipl.service.dto.matchsummary.bowling.Bowling;
import com.ipl.service.dto.matchsummary.fielding.Fielding;
import com.ipl.service.dto.Player;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchData {
	private Set<Fielding> fielding;
	private Set<Bowling> bowling;
	private Set<Batting> batting;
	private Set<Team> team;
	@JsonProperty("man-of-the-match")
	private Player manOfTheMatch;
	@JsonProperty("toss_winner_team")
	private String tossWinnerTeam;
	@JsonProperty("winner_team")
	private String winnerTeam;
	private boolean matchStarted;

	public Set<Fielding> getFielding() {
		return fielding;
	}

	public void setFielding(Set<Fielding> fielding) {
		this.fielding = fielding;
	}

	public Set<Bowling> getBowling() {
		return bowling;
	}

	public void setBowling(Set<Bowling> bowling) {
		this.bowling = bowling;
	}

	public Set<Batting> getBatting() {
		return batting;
	}

	public void setBatting(Set<Batting> batting) {
		this.batting = batting;
	}

	public Set<Team> getTeam() {
		return team;
	}

	public void setTeam(Set<Team> team) {
		this.team = team;
	}

	public Player getManOfTheMatch() {
		return manOfTheMatch;
	}

	public void setManOfTheMatch(Player manOfTheMatch) {
		this.manOfTheMatch = manOfTheMatch;
	}

	public String getTossWinnerTeam() {
		return tossWinnerTeam;
	}

	public void setTossWinnerTeam(String tossWinnerTeam) {
		this.tossWinnerTeam = tossWinnerTeam;
	}

	public String getWinnerTeam() {
		return winnerTeam;
	}

	public void setWinnerTeam(String winnerTeam) {
		this.winnerTeam = winnerTeam;
	}

	public boolean isMatchStarted() {
		return matchStarted;
	}

	public void setMatchStarted(boolean matchStarted) {
		this.matchStarted = matchStarted;
	}

	@Override
	public String toString() {
		return "MatchData{" +
				"fielding=" + fielding +
				", bowling=" + bowling +
				", batting=" + batting +
				", team=" + team +
				", manOfTheMatch=" + manOfTheMatch +
				", tossWinnerTeam='" + tossWinnerTeam + '\'' +
				", winnerTeam='" + winnerTeam + '\'' +
				", matchStarted=" + matchStarted +
				"}\n";
	}
}
