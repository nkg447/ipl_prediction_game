package com.ipl.model.entity;

import com.ipl.dao.PredictorDAO;

public class Predictor {
	private String name;
	private int authenticationId;
	private int score;

	public Predictor(String name, int authenticationId, int score) {
		this.name = name;
		this.authenticationId = authenticationId;
		this.score = score;
	}

	private static String autoGenerateId() {
		return String.valueOf(PredictorDAO.getAllPredictors().size() + 1);
	}

	public String getName() {
		return name;
	}

	public int getAuthenticationId() {
		return authenticationId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Predictor{" +
				"name='" + name + '\'' +
				", authId='" + authenticationId + '\'' +
				'}';
	}
}
