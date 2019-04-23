package com.ipl.form;

import java.util.List;

public class PredictionForm implements Validatable {

	private List<Prediction> predictions;

	public PredictionForm(List<Prediction> predictions) {
		this.predictions = predictions;
	}

	public List<Prediction> getPredictions() {
		return predictions;
	}

	@Override
	public void validate() throws ValidationException {
		for (Prediction prediction : predictions) {
			prediction.validate();
		}
	}

	public static class Prediction implements Validatable {
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

		@Override
		public void validate() throws ValidationException {
			FormUtil.validateAnswer(answer);
			FormUtil.validateId(questionId);
		}
	}
}
