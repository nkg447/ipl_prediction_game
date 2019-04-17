package com.ipl.model.entity;

public class Answer {
	private String id;
	private String predictionId;
	private String answer;
	private String questionId;

	public Answer(String id, String predictionId, String answer, String questionId) {
		this.id = id;
		this.predictionId = predictionId;
		this.answer = answer;
		this.questionId = questionId;
	}

	public String getId() {
		return id;
	}

	public String getPredictionId() {
		return predictionId;
	}

	public String getAnswer() {
		return answer;
	}

	public String getQuestionId() {
		return questionId;
	}
}
