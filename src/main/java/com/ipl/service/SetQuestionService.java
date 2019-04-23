package com.ipl.service;

import com.ipl.dao.QuestionDAO;
import com.ipl.form.QuestionsForm;
import com.ipl.model.entity.ModelUtil;
import com.ipl.model.entity.Question;

import java.sql.SQLException;

public class SetQuestionService {
	public static void setQuestions(QuestionsForm form) throws SQLException {
		for (QuestionsForm.Question question : form.getQuestions()) {
			QuestionDAO.save(new Question(
					question.getQuestion(),
					form.getDate(),
					question.getOptions().toString(),
					ModelUtil.questionTypeToEnum(question.getType()),
					question.getPoints()
			));
		}
	}
}
