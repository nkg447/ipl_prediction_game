package com.ipl.framework.validator;
//delete
public class PositiveNumberValidator extends NumberRangeValidator {

	private static final PositiveNumberValidator INSTANCE = new PositiveNumberValidator();

	public PositiveNumberValidator() {
		super(0, null);
	}
}
