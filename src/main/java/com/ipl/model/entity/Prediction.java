package com.ipl.model.entity;

public class Prediction {
	private String id;
	private String email;
	private String date;

	public Prediction(String id, String email, String date) {
		this.id = id;
		this.email = email;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}
}
