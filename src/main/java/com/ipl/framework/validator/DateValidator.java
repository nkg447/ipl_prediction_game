package com.ipl.framework.validator;

//yyyy-mm-dd
//pattern as arg
public class DateValidator extends RegexValidator {

	private static final String PATTERN =
			"^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
	private static final DateValidator INSTANCE = new DateValidator(PATTERN);

	private DateValidator(String regex) {
		super(regex);
	}

	public static DateValidator getInstance() {
		return INSTANCE;
	}
}
