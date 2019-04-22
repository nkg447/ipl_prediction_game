package com.ipl.dao;

import com.ipl.dao.util.DatabaseConnection;
import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Question;
import com.ipl.model.entity.QuestionType;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
	private final static Logger logger = Logger.getLogger(QuestionDAO.class);

	public static void save(Question question) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"insert into question (question, options, type, points) values (?, ?, ?, ?)"
				);
		preparedStatement.setString(1, question.getQuestion());
		preparedStatement.setString(2, question.getOptions());
		preparedStatement.setString(3, String.valueOf(question.getType()));
		preparedStatement.setInt(4, question.getPoints());
		Update.executeQuery(preparedStatement);
	}

	public static List<Question> getAllQuestions(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.QUESTION + " " + condition;
		List<Question> questions = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				questions.add(new Question(
						rs.getInt("id"),
						rs.getString("question"),
						rs.getString("date"),
						rs.getString("options"),
						questionTypeToEnum(rs.getString("type")),
						rs.getInt("points")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(questions);
		return questions;
	}

	private static QuestionType questionTypeToEnum(String type) {
		if(type.equals(QuestionType.INTEGER)) return QuestionType.INTEGER;
		if(type.equals(QuestionType.MULTIPLE_CHOICE)) return QuestionType.MULTIPLE_CHOICE;
		return QuestionType.STRING;
	}

	public static List<Question> getAllQuestions() {
		return getAllQuestions("");
	}

	public static List<Question> getQuestionsByDate(String date) {
		return getAllQuestions("WHERE DATE LIKE '" + date + "%'");
	}

	public static Question getQuestionById(int id) {
		return getAllQuestions("WHERE ID=" + id).remove(0);
	}
}
