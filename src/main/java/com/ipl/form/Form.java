package com.ipl.form;

import com.ipl.framework.validator.Validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract public class Form implements Populatable<Form>, Validatable {

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
		if (validation == null) return true;

		Class<? extends Validator> validatorClass = validation.validator();
		Validator validator = (Validator) validatorClass.getMethod("getInstance").invoke(null);

		if (!f.getType().isArray()) {
			return validateObjectField(f, validator);
		} else {
			return validateArrayField(f, validator);
		}
	}

	private boolean validateArrayField(Field f, Validator validator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class arrayClass = f.getType().getComponentType();
		Method fieldGetter = this.getClass().getMethod(getterMethodName(f));
		Object[] data = (Object[]) fieldGetter.invoke(this);
		for (Object o : data) {
			if (!validate(o, validator)) return false;
		}
		return true;
	}

	private boolean validateObjectField(Field f, Validator validator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method fieldGetter = this.getClass().getMethod(getterMethodName(f));
		Object data = fieldGetter.invoke(this);
		return validate(data, validator);
	}

	private boolean validate(Object data, Validator validator) {
		return validator.validate(data);
	}

	private static String getterMethodName(Field f) {
		String name = f.getName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return "get" + name;
	}
}
