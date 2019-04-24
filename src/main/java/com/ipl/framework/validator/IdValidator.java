package com.ipl.framework.validator;

public final class IdValidator extends NumberRangeValidator<Integer> {

	public static final IdValidator VALIDATOR = new IdValidator(0);

	private IdValidator(Integer min) {
		super(min, Integer.MAX_VALUE);
	}
}
