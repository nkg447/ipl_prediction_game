package com.ipl.form;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ipl.framework.validator.EmailValidator;
import com.ipl.framework.validator.PasswordValidator;

public class LoginForm extends Form {
	private static final EmailValidator EMAIL_VALIDATOR = new EmailValidator();
	private static final PasswordValidator PASSWORD_VALIDATOR = new PasswordValidator();

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
		return EMAIL_VALIDATOR.validate(email) &&
				PASSWORD_VALIDATOR.validate(password);
	}
}
