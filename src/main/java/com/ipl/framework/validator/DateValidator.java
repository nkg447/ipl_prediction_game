package com.ipl.framework.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements Validator<String> {

	private SimpleDateFormat format;

	public DateValidator(String pattern) {
		this.format = new SimpleDateFormat(pattern);
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	public DateValidator setFormat(SimpleDateFormat format) {
		this.format = format;
		return this;
	}

	@Override
	public boolean validate(String date) {
		try {
			format.parse(date);
			return true;
		}
		catch(ParseException e){
			return false;
		}
	}
}
