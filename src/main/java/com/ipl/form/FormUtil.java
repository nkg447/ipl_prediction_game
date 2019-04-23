package com.ipl.form;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormUtil {

	private static final String EMAIL_REGEX =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static void validateEmail(final String email) throws ValidationException {
		// TODO validate email
	}

	public static void validatePassword(String password) throws ValidationException {
		// TODO validate password
	}

	public static void validateName(String name) throws ValidationException {
		// TODO validate name
	}

	public static void validateAnswer(String answer) throws ValidationException {
		// TODO validate answer
	}

	public static void validateId(int id) throws ValidationException {
		// TODO validate id
	}

	public static void validateOption(String option) throws ValidationException {
		// TODO validate option
	}

	public static void validateQuestion(String question) throws ValidationException {
		// TODO validate question
	}

	public static void validatePoints(int points) throws ValidationException {
		// TODO validate points
	}

	public static void validateType(String type) throws ValidationException {
		// TODO validate type
	}

	public static void validateDate(String date) throws ValidationException {
		// TODO validate date
	}
}
