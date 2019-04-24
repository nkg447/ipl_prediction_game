package com.ipl.framework.validator;

public final class AgeValidator extends NumberRangeValidator<Integer> {

	public static final AgeValidator MINOR_VALIDATOR = new AgeValidator(1, 18);
	private static final int MAX_AGE = 100;
	public static final AgeValidator ADULT_VALIDATOR = new AgeValidator(18, MAX_AGE);
	public static final AgeValidator SENIOR_VALIDATOR = new AgeValidator(60, MAX_AGE);

	private AgeValidator(Integer min, Integer max) {
		super(min, max);
	}
}
