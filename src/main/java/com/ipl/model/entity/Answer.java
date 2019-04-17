package com.ipl.model.entity;

public class Answer {
	private int id;
	private int predictionId;
	private String answer;
	private int questionId;

	public Answer(int id, int predictionId, String answer, int questionId) {
		this.id = id;
		this.predictionId = predictionId;
		this.answer = answer;
		this.questionId = questionId;
	}

	public int getId() {
		return id;
	}

	public int getPredictionId() {
		return predictionId;
	}

	public String getAnswer() {
		return answer;
	}

	public int getQuestionId() {
		return questionId;
	}
}
