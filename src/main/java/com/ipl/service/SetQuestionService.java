package com.ipl.service;

import com.ipl.dao.QuestionDAO;
import com.ipl.form.QuestionsForm;
import com.ipl.model.entity.ModelUtil;
import com.ipl.model.entity.Question;

import java.sql.SQLException;

public class SetQuestionService {
	public static void setQuestions(QuestionsForm form) throws SQLException {
		for (QuestionsForm.QuestionForm questionForm : form.getQuestionForms()) {
			QuestionDAO.save(new Question(
					questionForm.getQuestion(),
					form.getDate(),
					questionForm.getOptions().toString(),
					ModelUtil.questionTypeToEnum(questionForm.getType()),
					questionForm.getPoints()
			));
		}
	}
}
