package com.ipl.model.entity;

import com.ipl.dao.PredictorDAO;

public class Predictor {
	private String name;
	private String authenticationId;
	private int score;

	public Predictor(String name, String authenticationId, int score) {
		this.name = name;
		this.authenticationId = authenticationId;
		this.score = score;
	}

	public Predictor(String name, boolean autoCreateAuthId, int score) {
		this.name = name;
		this.authenticationId = (autoCreateAuthId)? autoGenerateId() : "";
		this.score = score;
	}

	private static String autoGenerateId() {
		return String.valueOf(PredictorDAO.getAllPredictors().size()+1);
	}

	public String getName() {
		return name;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Predictor{" +
				"name='" + name + '\'' +
				", authId='" + authenticationId + '\'' +
				'}';
	}
}
