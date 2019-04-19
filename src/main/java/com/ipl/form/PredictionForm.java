package com.ipl.form;

import java.util.List;

public class PredictionForm {

	private List<Prediction> predictions;

	public PredictionForm(List<Prediction> predictions) {
		this.predictions = predictions;
	}

	public List<Prediction> getPredictions() {
		return predictions;
	}

	public static class Prediction{
		private int questionId;
		private String answer;

		public Prediction(int questionId, String answer) {
			this.questionId = questionId;
			this.answer = answer;
		}

		public int getQuestionId() {
			return questionId;
		}

		public String getAnswer() {
			return answer;
		}
	}
}
