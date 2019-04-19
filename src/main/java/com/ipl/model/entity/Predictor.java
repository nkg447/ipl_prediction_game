package com.ipl.model.entity;

import com.ipl.dao.AuthenticationDAO;

public class Predictor {
	public static final String ADMIN_EMAIL = "admin@admin";

	private int id;
	private Authentication authentication;
	private String name;
	private String tlm;
	private int score;
	private Status status;

	public Predictor(int authenticationId, String name) {
		this(0, authenticationId, name, "", 0, Status.ACTIVE);
	}

	public Predictor(int id, int authenticationId, String name, String tlm, int score, Status status) {
		this.id = id;
		this.authentication = AuthenticationDAO.getAuthenticationById(authenticationId);
		this.name = name;
		this.tlm = tlm;
		this.score = score;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public String getName() {
		return name;
	}

	public String getTlm() {
		return tlm;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Status getStatus() {
		return status;
	}
}
