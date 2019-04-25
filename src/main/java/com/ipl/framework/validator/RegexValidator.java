package com.ipl.framework.validator;

public abstract class RegexValidator implements StringValidator {
	private String regex;

	public RegexValidator(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean validate(String data) {
		return data.matches(regex);
	}
}
