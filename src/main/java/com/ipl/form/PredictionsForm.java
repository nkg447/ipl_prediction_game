package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.ipl.framework.validator.IdValidator;
import com.ipl.framework.validator.TextValidator;

import java.util.ArrayList;
import java.util.List;

public class PredictionsForm extends Form {

	private PredictionForm[] predictionForms;

	public PredictionsForm() {
	}

	public PredictionsForm(PredictionForm[] predictionForms) {
		this.predictionForms = predictionForms;
	}

	@Override
	public Form populate(JsonElement data) {
		JsonArray jsonElements = data.getAsJsonArray();
		List<PredictionForm> predictionForms = new ArrayList<>();

		for (JsonElement je : jsonElements) {
			predictionForms.add((PredictionForm) new PredictionForm().populate(je));
		}
		this.setPredictionForms(predictionForms.stream().toArray(PredictionForm[]::new));
		return this;
	}

	public PredictionForm[] getPredictionForms() {
		return predictionForms;
	}

	public void setPredictionForms(PredictionForm[] predictionForms) {
		this.predictionForms = predictionForms;
	}

	public static class PredictionForm extends Form {
		@Validation(name = "question-id", validator = IdValidator.class)
		private int questionId;
		@Validation(name = "answer", validator = TextValidator.class)
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
