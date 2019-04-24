package com.ipl.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RegisterForm extends Form {
	private String name;
	private String email;
	private String password;

	public RegisterForm() {
	}

	public RegisterForm(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public void validate() throws ValidationException {
		FormUtil.validateEmail(getEmail());
		FormUtil.validateName(getName());
		FormUtil.validatePassword(getPassword());
	}

	@Override
	public Form populate(JsonElement data) {
		JsonObject jsonObject = data.getAsJsonObject();
		this.setEmail(jsonObject.get("email").getAsString());
		this.setName(jsonObject.get("name").getAsString());
		this.setPassword(jsonObject.get("password").getAsString());
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
