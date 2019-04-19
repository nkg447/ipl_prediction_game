package com.ipl.model.entity;

public enum QuestionType {
	MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
	STRING("STRING"),
	INTEGER("INTEGER");
	private String type;
	QuestionType(String type){
		this.type=type;
	}

	public String getType() {
		return type;
	}
}
