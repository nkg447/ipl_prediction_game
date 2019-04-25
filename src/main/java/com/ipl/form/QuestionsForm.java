package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ipl.framework.validator.PositiveNumberValidator;
import com.ipl.framework.validator.QuestionValidator;
import com.ipl.framework.validator.TextValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionsForm extends Form {

	private QuestionForm[] questionForms;
	private String date;

	public QuestionsForm() {
	}

	public QuestionsForm(QuestionForm[] questionForms, String date) {
		this.questionForms = questionForms;
		this.date = date;
	}

	@Override
	public Form populate(JsonElement data) {
		JsonObject jsonObject = data.getAsJsonObject();
		String date = jsonObject.get("date").getAsString();
		List<QuestionForm> questionFormList = new ArrayList<>();

		JsonArray questions = jsonObject.getAsJsonArray("questionForms");
		for (JsonElement question : questions) {
			questionFormList.add((QuestionForm) new QuestionForm().populate(question));
		}

		this.setDate(date);
		this.setQuestionForms((QuestionForm[]) questionFormList.toArray());
		return this;
	}

	public QuestionForm[] getQuestionForms() {
		return questionForms;
	}

	public void setQuestionForms(QuestionForm[] questionForms) {
		this.questionForms = questionForms;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static class QuestionForm extends Form {
		@Validation(name = "question", validator = QuestionValidator.class)
		private String question;
		private String type;
		@Validation(name = "option", validator = TextValidator.class)
		private String[] options;
		@Validation(name = "points", validator = PositiveNumberValidator.class)
		private int points;

		public QuestionForm() {
		}

		public QuestionForm(String question, String type, String[] options, int points) {
			this.question = question;
			this.type = type;
			this.options = options;
			this.points = points;
		}

		public String getQuestion() {
			return question;
		}

		public String getType() {
			return type;
		}

		public String[] getOptions() {
			return options;
		}

		public int getPoints() {
			return points;
		}

		@Override
		public Form populate(JsonElement data) {
			Set<String> options = new HashSet<>();
			for (JsonElement option : data.getAsJsonObject().getAsJsonArray("options")) {
				options.add(option.getAsString());
			}
			return new QuestionForm(
					data.getAsJsonObject().get("question").getAsString(),
					data.getAsJsonObject().get("type").getAsString(),
					options.stream().toArray(String[]::new),
					data.getAsJsonObject().get("points").getAsInt()
			);
		}
	}
}
