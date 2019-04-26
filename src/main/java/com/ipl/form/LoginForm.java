package com.ipl.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LoginForm extends Form {
	private String email;
	private String password;

	public LoginForm() {

	}

	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void populate(JsonElement data) {
		JsonObject jsonObject = data.getAsJsonObject();
		this.setEmail(jsonObject.get("email").getAsString());
		this.setPassword(jsonObject.get("password").getAsString());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isValid() {
		return false;
	}
}
