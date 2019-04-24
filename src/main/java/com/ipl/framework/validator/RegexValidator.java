package com.ipl.framework.validator;

import java.util.regex.Pattern;

abstract class RegexValidator implements StringValidator {
	private Pattern pattern;

	RegexValidator(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	@Override
	public boolean validate(String data) {
		return false;
	}
}
