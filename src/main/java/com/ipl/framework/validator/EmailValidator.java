package com.ipl.framework.validator;

public final class EmailValidator extends RegexValidator {

	private static final String PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" + "@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final EmailValidator VALIDATOR = new EmailValidator(PATTERN);

	private EmailValidator(String regex) {
		super(regex);
	}
}
