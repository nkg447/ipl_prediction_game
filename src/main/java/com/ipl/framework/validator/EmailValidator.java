package com.ipl.framework.validator;

public class EmailValidator implements Validator<String> {

	private static final String PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" + "@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final RegexValidator VALIDATOR = new RegexValidator(PATTERN);

	@Override
	public boolean validate(String data) {
		return VALIDATOR.validate(data);
	}
}
