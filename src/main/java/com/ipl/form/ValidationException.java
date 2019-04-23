package com.ipl.form;

public class ValidationException extends Exception {
	// entity in which the validation exception occurred.
	private String entity;

	public ValidationException(String message, String entity) {
		super(message);
		this.entity = entity;
	}

	public ValidationException(String entity) {
		super("null");
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
}
