package com.ipl.dao;

import com.ipl.dao.util.DatabaseConnection;
import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Authentication;
import com.ipl.model.entity.Predictor;
import com.ipl.model.entity.Status;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictorDAO {

	private final static Logger logger = Logger.getLogger(PredictorDAO.class);

	public static void save(Predictor predictor) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"insert into predictor (AUTH_ID, NAME, SCORE, STATUS) values (?, ?, ?, ?)"
				);
		preparedStatement.setInt(1, predictor.getAuthentication().getId());
		preparedStatement.setString(2, predictor.getName());
		preparedStatement.setInt(3, predictor.getScore());
		preparedStatement.setString(4, String.valueOf(predictor.getStatus()));
		Update.executeQuery(preparedStatement);
	}

	public static void update(Predictor predictor) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"replace into predictor (AUTH_ID, SCORE) values (?, ?)"
				);
		preparedStatement.setInt(1, predictor.getAuthentication().getId());
		preparedStatement.setInt(2, predictor.getScore());
		Update.executeQuery(preparedStatement);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.PREDICTOR + "\" (\n" +
				"\t\"AUTHENTICATION_ID\"\tTEXT NOT NULL UNIQUE,\n" +
				"\t\"NAME\"\tTEXT NOT NULL,\n" +
				"\t\"SCORE\"\tINTEGER NOT NULL,\n" +
				"\tPRIMARY KEY(\"AUTHENTICATION_ID\")\n" +
				");";
		Update.executeQuery(query);
	}

	public static List<Predictor> getAllPredictors(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.PREDICTOR + " " + condition;
		List<Predictor> predictors = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				predictors.add(new Predictor(
						rs.getInt("id"),
						rs.getInt("auth_id"),
						rs.getString("name"),
						rs.getString("tlm"),
						rs.getInt("score"),
						rs.getString("status").equals(Status.ACTIVE) ?
								Status.ACTIVE : Status.INACTIVE
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(predictors);
		return predictors;
	}

	public static List<Predictor> getAllPredictors() {
		return getAllPredictors("");
	}

	public static Predictor getPredictorById(int id) {
		return getAllPredictors("WHERE ID=" + id).remove(0);
	}

	public static Predictor getPredictorByAuthId(int id) {
		return getAllPredictors("WHERE AUTH_ID=" + id).remove(0);
	}

	public static Predictor getPredictorByEmail(String email) {
		return PredictorDAO.getPredictorByAuthId(
				AuthenticationDAO.getAuthenticationByEmail(email).getId()
		);
	}

	public static void updateScore(int id, int points) throws SQLException {
		Predictor predictor = getPredictorById(id);
		predictor.setScore(predictor.getScore() + points);
		PredictorDAO.update(predictor);
	}
}
