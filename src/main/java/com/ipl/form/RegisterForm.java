package com.ipl.form;

public class RegisterForm implements Validatable {
	private String name;
	private String email;
	private String password;

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

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
