package com.ipl.model.entity;

import com.ipl.dao.PredictionDAO;
import com.ipl.dao.QuestionDAO;

public class Answer {
	private int id;
	private Prediction prediction;
	private String answerValue;
	private Question question;
	private String whenCreated;

	public Answer(int predictionId, String answerValue, int questionId) {
		this(0, predictionId, answerValue, questionId, "");
	}

	public Answer(int id, int predictionId, String answerValue, int questionId, String whenCreated) {
		this.id = id;
		this.prediction = PredictionDAO.getPredictionById(predictionId);
		this.answerValue = answerValue;
		this.question = QuestionDAO.getQuestionById(questionId);
		this.whenCreated = whenCreated;
	}

	public int getId() {
		return id;
	}

	public Prediction getPrediction() {
		return prediction;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public Question getQuestion() {
		return question;
	}

	public String getWhenCreated() {
		return whenCreated;
	}
}
