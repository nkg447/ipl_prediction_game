package com.ipl.framework.validator;

public final class IdValidator extends NumberRangeValidator<Integer> {

	private static final IdValidator INSTANCE = new IdValidator(0);

	private IdValidator(Integer min) {
		super(min, Integer.MAX_VALUE);
	}

	public static IdValidator getInstance() {
		return INSTANCE;
	}
}
