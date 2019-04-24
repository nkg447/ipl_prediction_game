package com.ipl.form;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionsForm extends Form{

	private List<Question> questions;
	private String date;

	public QuestionsForm() {
	}

	public QuestionsForm(List<Question> questions, String date) {
		this.questions = questions;
		this.date = date;
	}

	@Override
	public void validate() throws ValidationException {
		for(Question question : questions)
			question.validate();
		FormUtil.validateDate(date);
	}

	@Override
	public Form populate(JsonElement data) {
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
		return this;
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

	public static class Question implements Validatable{
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
		public void validate() throws ValidationException {
			FormUtil.validateQuestion(question);
			for(String option : options){
				FormUtil.validateOption(option);
			}
			FormUtil.validatePoints(points);
			FormUtil.validateType(type);
		}
	}
}
