package com.ipl.dao;

import com.ipl.model.entity.Answer;
import com.ipl.dao.sql.DatabaseInfo;
import com.ipl.dao.sql.Query;
import com.ipl.dao.sql.Update;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {
	private final static Logger logger = Logger.getLogger(AnswerDAO.class);

	public static void save(Answer answer) {
		String query = "REPLACE INTO " + DatabaseInfo.ANSWER + " VALUES(" +
				"'" + answer.getId() + "'," +
				"'" + answer.getPredictionId() + "'," +
				"'" + answer.getAnswer() + "'," +
				"'" + answer.getQuestionId() + "')";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \""+DatabaseInfo.ANSWER+"\" (\n" +
				"\t\"ID\"\tTEXT NOT NULL UNIQUE,\n" +
				"\t\"PREDICTION_ID\"\tTEXT NOT NULL,\n" +
				"\t\"ANSWER\"\tTEXT NOT NULL,\n" +
				"\t\"QUESTION_ID\"\tTEXT NOT NULL,\n" +
				"\tPRIMARY KEY(\"ID\")\n" +
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
						rs.getString("ID"),
						rs.getString("PREDICTION_ID"),
						rs.getString("ANSWER"),
						rs.getString("QUESTION_ID")
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

	public static List<Answer> getAnswersByPredictionId(String predictionId) {
		return getAllAnswers("WHERE PREDICTION_ID='" + predictionId + "'");
	}

	public static Answer getAnswerById(String id) {
		return getAllAnswers("WHERE ID='" + id + "'").remove(0);
	}
}
