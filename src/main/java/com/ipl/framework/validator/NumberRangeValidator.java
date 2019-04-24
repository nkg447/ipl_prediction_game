package com.ipl.framework.validator;

public class NumberRangeValidator<T extends Number & Comparable> extends RangeValidator<T> {
	NumberRangeValidator(T min, T max) {
		super(min, max);
	}
}
