package com.ipl.framework.validator;

public final class PasswordValidator extends RegexValidator {
	private static final String PATTERN =
			"((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	public static final PasswordValidator VALIDATOR = new PasswordValidator(PATTERN);

	private PasswordValidator(String regex) {
		super(regex);
	}
}
