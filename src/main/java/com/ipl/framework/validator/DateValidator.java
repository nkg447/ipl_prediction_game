package com.ipl.framework.validator;

public class DateValidator extends RegexValidator {

	private static final String PATTERN =
			"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
	public static final DateValidator VALIDATOR = new DateValidator(PATTERN);

	DateValidator(String regex) {
		super(regex);
	}
}
