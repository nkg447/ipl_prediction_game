package com.ipl.framework.validator;

public abstract class RangeValidator<T extends Comparable> implements Validator<T> {
	private T min;
	private T max;

	public RangeValidator(T min, T max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean validate(T data) {
		return ((min == null || data.compareTo(min) >= 0) &&
				(max == null || max.compareTo(data) >= 0)
		);
	}
}
