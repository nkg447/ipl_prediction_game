package com.ipl.controller.data.matchsummary.fielding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldingScore {
	private String name;
	private String runout;
	private String stumped;
	private String bowled;
	private String lbw;
	private String _catch;
	private String pid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRunout() {
		return runout;
	}

	public void setRunout(String runout) {
		this.runout = runout;
	}

	public String getStumped() {
		return stumped;
	}

	public void setStumped(String stumped) {
		this.stumped = stumped;
	}

	public String getBowled() {
		return bowled;
	}

	public void setBowled(String bowled) {
		this.bowled = bowled;
	}

	public String getLbw() {
		return lbw;
	}

	public void setLbw(String lbw) {
		this.lbw = lbw;
	}

	public String getCatch() {
		return _catch;
	}

	public void setCatch(String _catch) {
		this._catch = _catch;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
