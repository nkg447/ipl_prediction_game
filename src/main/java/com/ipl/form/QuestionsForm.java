package com.ipl.form;

import java.util.List;
import java.util.Set;

public class QuestionsForm implements Validatable {

	private List<Question> questions;
	private String date;

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

	public List<Question> getQuestions() {
		return questions;
	}

	public String getDate() {
		return date;
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
