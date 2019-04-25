package com.ipl.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ipl.framework.validator.EmailValidator;
import com.ipl.framework.validator.PasswordValidator;

public class LoginForm extends Form {
	@Validation(name = "email", validator = EmailValidator.class)
	private String email;
	@Validation(name = "password", validator = PasswordValidator.class)
	private String password;

	public LoginForm() {

	}

	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Form populate(JsonElement data) {
		JsonObject jsonObject = data.getAsJsonObject();
		this.setEmail(jsonObject.get("email").getAsString());
		this.setPassword(jsonObject.get("password").getAsString());
		return this;
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
