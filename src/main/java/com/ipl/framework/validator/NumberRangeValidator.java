package com.ipl.framework.validator;
//delete
public abstract class NumberRangeValidator<T extends Number & Comparable> extends RangeValidator<T> {
	public NumberRangeValidator(T min, T max) {
		super(min, max);
	}
}
