package com.ipl.framework.validator;

public final class PasswordValidator extends RegexValidator {
	private static final String PATTERN =
			"((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	private static final PasswordValidator INSTANCE = new PasswordValidator(PATTERN);

	private PasswordValidator(String regex) {
		super(regex);
	}

	public static PasswordValidator getInstance() {
		return INSTANCE;
	}
}
