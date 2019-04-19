package com.ipl.model.entity;

public class Authentication {
	private int id;
	private String email;
	private String password;
	private String tlm;
	private String whenCreated;

	public Authentication(String email, String password) {
		this(0, email, password, "0", "0");
	}

	public Authentication(int id, String email, String password, String tlm, String whenCreated) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.tlm = tlm;
		this.whenCreated = whenCreated;
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

	public String getTlm() {
		return tlm;
	}

	public String getWhenCreated() {
		return whenCreated;
	}
}
