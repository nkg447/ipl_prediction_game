package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class PredictionsForm extends Form {

	private List<PredictionForm> predictionForms;

	public PredictionsForm() {
	}

	public PredictionsForm(List<PredictionForm> predictionForms) {
		this.predictionForms = predictionForms;
	}

	@Override
	public Form populate(JsonElement data) {
		JsonArray jsonElements = data.getAsJsonArray();
		List<PredictionForm> predictionForms = new ArrayList<>();

		for (JsonElement je : jsonElements) {
			predictionForms.add((PredictionForm) new PredictionForm().populate(je));
		}
		this.setPredictionForms(predictionForms);
		return this;
	}

	public List<PredictionForm> getPredictionForms() {
		return predictionForms;
	}

	public void setPredictionForms(List<PredictionForm> predictionForms) {
		this.predictionForms = predictionForms;
	}

	public static class PredictionForm extends Form {
		private int questionId;
		private String answer;

		public PredictionForm() {
		}

		public PredictionForm(int questionId, String answer) {
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
		public Form populate(JsonElement data) {
			String answer = "";
			if (data.getAsJsonObject().get("answer").isJsonArray()) {
				answer = data.getAsJsonObject().get("answer").getAsJsonArray().toString();
			} else
				answer = data.getAsJsonObject().get("answer").getAsString();

			return new PredictionForm(
					data.getAsJsonObject().get("id").getAsInt(),
					answer
			);
		}
	}
}
