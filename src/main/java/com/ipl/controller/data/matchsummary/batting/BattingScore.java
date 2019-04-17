package com.ipl.controller.data.matchsummary.batting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ipl.controller.data.Player;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BattingScore {
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonProperty("dismissal-by")
	private Set<Player> dismissalBy;
	private String dismissal;
	@JsonProperty("SR")
	private String SR;
	private int sixs;
	private int fours;
	@JsonProperty("B")
	private String Balls;
	@JsonProperty("R")
	private String Runs;
	@JsonProperty("dismissal-info")
	private String dismissalInfo;
	private String detail;
	private String batsman;
	private String pid;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Set<Player> getDismissalBy() {
		return dismissalBy;
	}

	public void setDismissalBy(Set<Player> dismissalBy) {
		this.dismissalBy = dismissalBy;
	}

	public String getDismissal() {
		return dismissal;
	}

	public void setDismissal(String dismissal) {
		this.dismissal = dismissal;
	}

	public String getSR() {
		return SR;
	}

	public void setSR(String SR) {
		this.SR = SR;
	}

	public int get6s() {
		return sixs;
	}

	public void set6s(int sixs) {
		this.sixs = sixs;
	}

	public int get4s() {
		return fours;
	}

	public void set4s(int fours) {
		this.fours = fours;
	}

	public String getBalls() {
		return Balls;
	}

	public void setBalls(String balls) {
		Balls = balls;
	}

	public String getRuns() {
		return Runs;
	}

	public void setRuns(String runs) {
		Runs = runs;
	}

	public String getDismissalInfo() {
		return dismissalInfo;
	}

	public void setDismissalInfo(String dismissalInfo) {
		this.dismissalInfo = dismissalInfo;
	}

	public String getBatsman() {
		return batsman;
	}

	public void setBatsman(String batsman) {
		this.batsman = batsman;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "BattingScore{" +
				"dismissalBy=" + dismissalBy +
				", dismissal='" + dismissal + '\'' +
				", SR='" + SR + '\'' +
				", sixs=" + sixs +
				", fours=" + fours +
				", Balls='" + Balls + '\'' +
				", Runs='" + Runs + '\'' +
				", dismissalInfo='" + dismissalInfo + '\'' +
				", batsman='" + batsman + '\'' +
				", pid='" + pid + '\'' +
				"}\n";
	}
}
