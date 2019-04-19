package com.ipl.dao;

import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {
	private final static Logger logger = Logger.getLogger(AnswerDAO.class);

	public static void save(Answer answer) {
		String query = "REPLACE INTO " + DatabaseInfo.ANSWER
				+ "(PREDICTION_ID, ANSWER, QUESTION_ID) VALUES(" +
				answer.getPredictionId() + "," +
				"'" + answer.getAnswerValue() + "'," +
				answer.getQuestionId() + ")";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.ANSWER + "\" (\n" +
				"\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
				"\t\"PREDICTION_ID\"\tTEXT NOT NULL,\n" +
				"\t\"ANSWER\"\tTEXT NOT NULL,\n" +
				"\t\"QUESTION_ID\"\tTEXT NOT NULL\n" +
				");";
		Update.executeQuery(query);
	}

	public static List<Answer> getAllAnswers(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.ANSWER + " " + condition;
		List<Answer> answers = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				answers.add(new Answer(
						rs.getInt("ID"),
						rs.getInt("PREDICTION_ID"),
						rs.getString("ANSWER"),
						rs.getInt("QUESTION_ID")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(answers);
		return answers;
	}

	public static List<Answer> getAllAnswers() {
		return getAllAnswers("");
	}

	public static List<Answer> getAnswersByPredictionId(int predictionId) {
		return getAllAnswers("WHERE PREDICTION_ID=" + predictionId);
	}

	public static Answer getAnswerById(int id) {
		return getAllAnswers("WHERE ID=" + id).remove(0);
	}
}
