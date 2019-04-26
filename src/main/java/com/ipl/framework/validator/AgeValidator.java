package com.ipl.framework.validator;

public final class AgeValidator extends NumberRangeValidator<Integer> {

	//delete
	private static final int MAX_AGE = 123;
	private static final AgeValidator INSTANCE = new AgeValidator(18, MAX_AGE);

	private AgeValidator(Integer min, Integer max) {
		super(min, max);
	}

	public static AgeValidator getInstance() {
		return INSTANCE;
	}
}
