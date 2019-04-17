package com.ipl.model.entity;

public class Prediction {
	private int id;
	private String email;
	private String date;

	public Prediction(int id, String email, String date) {
		this.id = id;
		this.email = email;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}
}
