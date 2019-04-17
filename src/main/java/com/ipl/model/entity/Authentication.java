package com.ipl.model.entity;

public class Authentication {
	private int id;
	private String email;
	private String password;

	public Authentication(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
