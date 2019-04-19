package com.ipl.model.entity;

public class Question {
	private int id;
	private String question;
	private String date;
	private String options;
	private QuestionType type;
	private int points;

	public Question(String question, String date, String options, QuestionType type, int points) {
		this(0, question, date, options, type, points);
	}

	public Question(int id, String question, String date, String options, QuestionType type, int points) {
		this.id = id;
		this.question = question;
		this.date = date;
		this.options = options;
		this.type = type;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getDate() {
		return date;
	}

	public String getOptions() {
		return options;
	}

	public QuestionType getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}
}
