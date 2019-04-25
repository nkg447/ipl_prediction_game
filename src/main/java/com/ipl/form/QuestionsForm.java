package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionsForm extends Form {

	private List<QuestionForm> questionForms;
	private String date;

	public QuestionsForm() {
	}

	public QuestionsForm(List<QuestionForm> questionForms, String date) {
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
		this.setQuestionForms(questionFormList);
		return this;
	}

	public List<QuestionForm> getQuestionForms() {
		return questionForms;
	}

	public void setQuestionForms(List<QuestionForm> questionForms) {
		this.questionForms = questionForms;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static class QuestionForm extends Form {
		private String question;
		private String type;
		private Set<String> options;
		private int points;

		public QuestionForm() {
		}

		public QuestionForm(String question, String type, Set<String> options, int points) {
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

		public Set<String> getOptions() {
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
					options,
					data.getAsJsonObject().get("points").getAsInt()
			);
		}
	}
}
