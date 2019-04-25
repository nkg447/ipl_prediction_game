package com.ipl.form;

import com.ipl.framework.validator.Validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract public class Form implements Populatable<Form>, Validatable {

	private static String getterMethodName(Field f) {
		String name = f.getName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return "get" + name;
	}

	private static Object getFieldValue(Field f, Form form) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method fieldGetter = form.getClass().getMethod(getterMethodName(f));
		return fieldGetter.invoke(form);
	}

	private static boolean isValidatable(Field f) {
		return Validatable.class.isAssignableFrom(f.getType());
	}

	private static boolean isValidatableArray(Field f) {
		Class arrayClass = f.getType().getComponentType();
		return Validatable.class.isAssignableFrom(arrayClass);
	}

	@Override
	public boolean isValid() {
		try {
			return validateUsingReflection();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateUsingReflection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class validator = this.getClass();
		Field[] fields = validator.getDeclaredFields();

		for (Field f : fields) {
			if (!validate(f)) {
				return false;
			}
		}
		return true;
	}

	private boolean validate(Field f) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Validation validation = f.getAnnotation(Validation.class);
		if (validation == null) {
			if (f.getType().isArray()) {
				return isValidatableArray(f) && validateValidatableArray(f);
			} else {
				return isValidatable(f) && validateValidatableObject(f);
			}
		}

		Class<? extends Validator> validatorClass = validation.validator();
		Validator validator = (Validator) validatorClass.getMethod("getInstance").invoke(null);

		if (f.getType().isArray()) {
			return validateArrayField(f, validator);
		} else {
			return validateObjectField(f, validator);
		}
	}

	private boolean validateValidatableObject(Field f) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Validatable validatable = (Validatable) getFieldValue(f, this);
		return validatable.isValid();
	}

	private boolean validateValidatableArray(Field f) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Validatable[] validatables = (Validatable[]) getFieldValue(f, this);
		for (Validatable validatable : validatables) {
			if (!validatable.isValid()) return false;
		}
		return true;
	}

	private boolean validateArrayField(Field f, Validator validator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object[] data = (Object[]) getFieldValue(f, this);
		for (Object o : data) {
			if (!validate(o, validator)) return false;
		}
		return true;
	}

	private boolean validateObjectField(Field f, Validator validator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object data = getFieldValue(f, this);
		return validate(data, validator);
	}

	private boolean validate(Object data, Validator validator) {
		try {
			return validator.validate(data);
		} catch (ClassCastException e) {
			e.printStackTrace();
			return true;
		}
	}
}
