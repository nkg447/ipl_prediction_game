package com.ipl.service;

import com.ipl.dao.QuestionDAO;
import com.ipl.model.entity.Question;

import java.util.List;

public class Service {
	public static List<Question> getQuestions(String date) {
		List<Question> questions = QuestionDAO.getQuestionsByDate(date);
		return questions;
	}
}
