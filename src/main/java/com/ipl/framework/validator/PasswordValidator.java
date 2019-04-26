package com.ipl.framework.validator;

public final class PasswordValidator implements Validator<String> {
	private static final String PATTERN =
			"((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	private static final RegexValidator VALIDATOR = new RegexValidator(PATTERN);

	@Override
	public boolean validate(String data) {
		return VALIDATOR.validate(data);
	}
}
