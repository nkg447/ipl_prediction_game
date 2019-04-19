package com.ipl.service.dto.matchsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchSummary {
	private String dateTimeGMT;
	private MatchData data;
	private String type;

	public String getDateTimeGMT() {
		return dateTimeGMT;
	}

	public void setDateTimeGMT(String dateTimeGMT) {
		this.dateTimeGMT = dateTimeGMT;
	}

	public MatchData getData() {
		return data;
	}

	public void setData(MatchData data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "MatchSummary{" +
				"dateTimeGMT='" + dateTimeGMT + '\'' +
				", dto=" + data +
				", type='" + type + '\'' +
				"}\n";
	}
}
