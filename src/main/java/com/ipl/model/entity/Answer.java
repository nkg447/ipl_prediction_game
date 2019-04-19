package com.ipl.model.entity;

public class Answer {
	private int id;
	private int predictionId;
	private String answerValue;
	private int questionId;

	public Answer(int id, int predictionId, String answerValue, int questionId) {
		this.id = id;
		this.predictionId = predictionId;
		this.answerValue = answerValue;
		this.questionId = questionId;
	}

	public int getId() {
		return id;
	}

	public int getPredictionId() {
		return predictionId;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public int getQuestionId() {
		return questionId;
	}
}
