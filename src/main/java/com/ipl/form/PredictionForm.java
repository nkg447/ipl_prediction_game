package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class PredictionForm extends Form {

	private List<Prediction> predictions;

	public PredictionForm() {
	}

	public PredictionForm(List<Prediction> predictions) {
		this.predictions = predictions;
	}

	@Override
	public Form populate(JsonElement data) {
		JsonArray jsonElements = data.getAsJsonArray();
		List<PredictionForm.Prediction> predictions = new ArrayList<>();

		for (JsonElement je : jsonElements) {
			String answer = "";
			if (je.getAsJsonObject().get("answer").isJsonArray()) {
				answer = je.getAsJsonObject().get("answer").getAsJsonArray().toString();
			} else
				answer = je.getAsJsonObject().get("answer").getAsString();
			predictions.add(new PredictionForm.Prediction(
					je.getAsJsonObject().get("id").getAsInt(),
					answer
			));
		}
		this.setPredictions(predictions);
		return this;
	}

	public List<Prediction> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<Prediction> predictions) {
		this.predictions = predictions;
	}

	public static class Prediction {
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
