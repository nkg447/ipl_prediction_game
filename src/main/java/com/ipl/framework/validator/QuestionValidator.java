package com.ipl.framework.validator;

public class QuestionValidator extends RegexValidator {

	private static final String PATTERN = "([a-zA-Z]+)(.*)(\\?)";
	private static final QuestionValidator INSTANCE = new QuestionValidator(PATTERN);

	QuestionValidator(String regex) {
		super(regex);
	}

	public static QuestionValidator getInstance() {
		return INSTANCE;
	}
}
