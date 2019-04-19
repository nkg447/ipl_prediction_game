package com.ipl.model.entity;

import com.ipl.dao.AuthenticationDAO;

public class Predictor {
	public static final String ADMIN_EMAIL = "admin@admin";
	private String name;
	private Authentication authentication;
	private int score;

	public Predictor(String name, int authenticationId, int score) {
		this.name = name;
		this.authentication = AuthenticationDAO.getAuthenticationById(authenticationId);
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public Authentication getAuthentication() {
		return authentication;
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
				", authId='" + authentication + '\'' +
				'}';
	}
}
