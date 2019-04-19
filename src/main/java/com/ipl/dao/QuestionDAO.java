package com.ipl.dao;

import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Question;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
	private final static Logger logger = Logger.getLogger(QuestionDAO.class);

	public static void save(Question question) {
		String query = "REPLACE INTO " + DatabaseInfo.QUESTION
				+ "(QUESTION, DATE, OPTIONS, TYPE, POINTS) VALUES(" +
				"'" + question.getQuestion() + "'," +
				"'" + question.getDate() + "'," +
				"'" + question.getOptions() + "'," +
				"'" + question.getType() + "'," +
				"" + question.getPoints() + ")";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.QUESTION + "\" (\n" +
				"\t\"ID\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
				"\t\"QUESTION\"\tTEXT NOT NULL,\n" +
				"\t\"DATE\"\tTEXT NOT NULL,\n" +
				"\t\"OPTIONS\"\tTEXT,\n" +
				"\t\"TYPE\"\tTEXT NOT NULL,\n" +
				"\t\"POINTS\"\tINTEGER NOT NULL\n" +
				");";
		Update.executeQuery(query);
	}

	public static List<Question> getAllQuestions(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.QUESTION + " " + condition;
		List<Question> questions = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				questions.add(new Question(
						rs.getInt("ID"),
						rs.getString("QUESTION"),
						rs.getString("DATE"),
						rs.getString("OPTIONS"),
						rs.getString("TYPE"),
						rs.getInt("POINTS")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(questions);
		return questions;
	}

	public static List<Question> getAllQuestions() {
		return getAllQuestions("");
	}

	public static List<Question> getQuestionsByDate(String date) {
		return getAllQuestions("WHERE DATE='" + date + "'");
	}

	public static Question getQuestionById(int id) {
		return getAllQuestions("WHERE ID=" + id).remove(0);
	}
}
