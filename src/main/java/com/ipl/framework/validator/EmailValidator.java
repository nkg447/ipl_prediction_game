package com.ipl.framework.validator;

public final class EmailValidator extends RegexValidator {

	private static final String PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" + "@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final EmailValidator INSTANCE = new EmailValidator(PATTERN);

	private EmailValidator(String regex) {
		super(regex);
	}

	public static EmailValidator getInstance() {
		return INSTANCE;
	}
}
