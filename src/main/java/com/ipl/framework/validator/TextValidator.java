package com.ipl.framework.validator;

public class TextValidator extends RegexValidator {

	private static final String QUESTION_PATTERN = "([a-zA-Z]+)(.*)(\\?)";
	public static final TextValidator QUESTION_VALIDATOR = new TextValidator(QUESTION_PATTERN);

	private static final String ANSWER_PATTERN = "(.+)(.*)";
	public static final TextValidator ANSWER_VALIDATOR = new TextValidator(ANSWER_PATTERN);

	TextValidator(String regex) {
		super(regex);
	}
}
