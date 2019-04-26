package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ipl.framework.validator.DateValidator;
import com.ipl.framework.validator.QuestionValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionsForm extends Form {

	private static final DateValidator DATE_VALIDATOR = new DateValidator("yyyy-mm-dd");
	private List<Question> questions;
	private String date;

	public QuestionsForm() {
	}

	public QuestionsForm(List<Question> questions, String date) {
		this.questions = questions;
		this.date = date;
	}

	@Override
	public void populate(JsonElement data) {
		JsonObject jsonObject = data.getAsJsonObject();
		String date = jsonObject.get("date").getAsString();
		List<QuestionsForm.Question> questionList = new ArrayList<>();

		JsonArray questions = jsonObject.getAsJsonArray("questions");
		for (JsonElement question : questions) {
			Set<String> options = new HashSet<>();
			for (JsonElement option : question.getAsJsonObject().getAsJsonArray("options")) {
				options.add(option.getAsString());
			}
			questionList.add(new QuestionsForm.Question(
					question.getAsJsonObject().get("question").getAsString(),
					question.getAsJsonObject().get("type").getAsString(),
					options,
					question.getAsJsonObject().get("points").getAsInt()
			));
		}

		this.setDate(date);
		this.setQuestions(questionList);
	}

	@Override
	public boolean isValid() {
		return DATE_VALIDATOR.validate(date) &&
				questions.stream()
						.map(Question::isValid)
						.reduce((a, b) -> a && b)
						.get();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static class Question implements Validatable {
		private static final QuestionValidator QUESTION_VALIDATOR = new QuestionValidator();
		private String question;
		private String type;
		private Set<String> options;
		private int points;

		public Question(String question, String type, Set<String> options, int points) {
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
		public boolean isValid() {
			return validateType(type) &&
					QUESTION_VALIDATOR.validate(question) &&
					points > 0 &&
					((!type.equals("MULTIPLE_CHOICE")) || options.size() > 0);
		}

		private boolean validateType(String type) {
			return type.equals("MULTIPLE_CHOICE") ||
					type.equals("INTEGER") ||
					type.equals("STRING");
		}
	}
}
