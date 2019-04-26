package com.ipl.framework.validator;

public class QuestionValidator implements Validator<String> {

	private static final String PATTERN = "([a-zA-Z]+)(.*)(\\?)";
	private static final RegexValidator VALIDATOR = new RegexValidator(PATTERN);

	@Override
	public boolean validate(String data) {
		return VALIDATOR.validate(data);
	}
}
