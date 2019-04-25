package com.ipl.framework.validator;

public final class AgeValidator extends NumberRangeValidator<Integer> {

	private static final int MAX_AGE = 100;
	private static final AgeValidator INSTANCE = new AgeValidator(18, MAX_AGE);

	private AgeValidator(Integer min, Integer max) {
		super(min, max);
	}

	public static AgeValidator getInstance(){
		return INSTANCE;
	}
}
