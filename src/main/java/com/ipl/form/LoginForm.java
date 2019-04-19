package com.ipl.form;

public class LoginForm implements Validatable {
	private String email;
	private String password;

	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void validate() throws ValidationException {
		FormUtil.validateEmail(getEmail());
		FormUtil.validatePassword(getPassword());
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
