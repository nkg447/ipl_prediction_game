package com.ipl.dao;

import com.ipl.dao.util.DatabaseConnection;
import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {
	private final static Logger logger = Logger.getLogger(AnswerDAO.class);

	public static void save(Answer answer) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"insert into answer (prediction_id, answer_value, question_id) values (?, ?, ?)"
				);
		preparedStatement.setInt(1, answer.getPrediction().getId());
		preparedStatement.setString(2, answer.getAnswerValue());
		preparedStatement.setInt(3, answer.getQuestion().getId());
		Update.executeQuery(preparedStatement);
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
						rs.getString("answer_value"),
						rs.getInt("QUESTION_ID"),
						rs.getString("whenCreated")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return answers;
	}

	public static List<Answer> getAllAnswers() {
		return getAllAnswers("");
	}

	public static List<Answer> getAnswersByPredictionId(int predictionId) {
		return getAllAnswers("WHERE prediction_id=" + predictionId);
	}

	public static Answer getAnswerById(int id) {
		return getAllAnswers("WHERE ID=" + id).remove(0);
	}
}
