package com.ipl.framework.validator;

public class TextValidator extends RegexValidator {

	private static final String PATTERN = "(.+)(.*)";
	private static final TextValidator INSTANCE = new TextValidator(PATTERN);

	private TextValidator(String regex) {
		super(regex);
	}

	public static TextValidator getInstance() {
		return INSTANCE;
	}
}
