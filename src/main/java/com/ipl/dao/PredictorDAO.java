package com.ipl.dao;

import com.ipl.model.entity.Authentication;
import com.ipl.model.entity.Predictor;
import com.ipl.dao.sql.DatabaseInfo;
import com.ipl.dao.sql.Query;
import com.ipl.dao.sql.Update;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictorDAO {

	private final static Logger logger = Logger.getLogger(PredictorDAO.class);

	public static void save(Predictor predictor) {
		String query = "INSERT INTO " + DatabaseInfo.PREDICTOR + " VALUES(" +
				"'" + predictor.getAuthenticationId() + "'," +
				"'" + predictor.getName() + "'," +
				"" + predictor.getScore() + ")";
		Update.executeQuery(query);
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
						rs.getString("NAME"),
						rs.getString("AUTHENTICATION_ID"),
						rs.getInt("SCORE")
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

	public static Predictor getPredictorByEmail(String email) {
		Authentication auth = AuthenticationDAO.getAuthenticationByEmail(email);
		return getPredictorByAuthId(auth.getId());
	}

	private static Predictor getPredictorByAuthId(String id) {
		return getAllPredictors("WHERE AUTHENTICATION_ID='" + id + "'").remove(0);
	}
}
