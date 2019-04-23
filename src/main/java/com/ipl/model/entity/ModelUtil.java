package com.ipl.model.entity;

public class ModelUtil {
	public static QuestionType questionTypeToEnum(String type) {
		if (type.equals(QuestionType.INTEGER.getType())) return QuestionType.INTEGER;
		if (type.equals(QuestionType.MULTIPLE_CHOICE.getType())) return QuestionType.MULTIPLE_CHOICE;
		return QuestionType.STRING;
	}
}
