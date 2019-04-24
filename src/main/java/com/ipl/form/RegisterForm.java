package com.ipl.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RegisterForm extends LoginForm {
	private String name;

	public RegisterForm() {
	}

	public RegisterForm(String name, String email, String password) {
		super(email, password);
		this.name = name;
	}

	@Override
	public void validate() throws ValidationException {
		super.validate();
		FormUtil.validateName(getName());
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
}
