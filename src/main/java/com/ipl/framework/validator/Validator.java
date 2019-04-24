package com.ipl.framework.validator;

public interface Validator<T> {
	boolean validate(T data);
}
