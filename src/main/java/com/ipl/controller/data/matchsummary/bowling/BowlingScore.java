package com.ipl.controller.data.matchsummary.bowling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BowlingScore {
	private int sixs;
	private int fours;
	private int zeros;
	@JsonProperty("Econ")
	private String Econ;
	@JsonProperty("W")
	private String Wickets;
	@JsonProperty("R")
	private String R;
	@JsonProperty("M")
	private String M;
	@JsonProperty("O")
	private String O;
	private String bowler;
	private String pid;

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

	public int get0s() {
		return zeros;
	}

	public void set0s(int zeros) {
		this.zeros = zeros;
	}

	public String getEcon() {
		return Econ;
	}

	public void setEcon(String econ) {
		Econ = econ;
	}

	public String getWickets() {
		return Wickets;
	}

	public void setWickets(String wickets) {
		Wickets = wickets;
	}

	public String getR() {
		return R;
	}

	public void setR(String r) {
		R = r;
	}

	public String getM() {
		return M;
	}

	public void setM(String m) {
		M = m;
	}

	public String getO() {
		return O;
	}

	public void setO(String o) {
		O = o;
	}

	public String getBowler() {
		return bowler;
	}

	public void setBowler(String bowler) {
		this.bowler = bowler;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "BowlingScore{" +
				"sixs=" + sixs +
				", fours=" + fours +
				", zeros=" + zeros +
				", Econ='" + Econ + '\'' +
				", Wickets='" + Wickets + '\'' +
				", R='" + R + '\'' +
				", M='" + M + '\'' +
				", O='" + O + '\'' +
				", bowler='" + bowler + '\'' +
				", pid='" + pid + '\'' +
				"}\n";
	}
}
