package com.ipl.dao;

import com.ipl.model.entity.Prediction;
import com.ipl.dao.sql.DatabaseInfo;
import com.ipl.dao.sql.Query;
import com.ipl.dao.sql.Update;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictionDAO {
	private final static Logger logger = Logger.getLogger(PredictionDAO.class);

	public static void save(Prediction prediction) {
		String query = "REPLACE INTO " + DatabaseInfo.PREDICTION + " VALUES(" +
				"'" + prediction.getId() + "'," +
				"'" + prediction.getEmail() + "'," +
				"'" + prediction.getDate() + "')";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.PREDICTION + "\" (\n" +
				"\t\"ID\"\tTEXT NOT NULL UNIQUE,\n" +
				"\t\"EMAIL\"\tTEXT NOT NULL,\n" +
				"\t\"DATE\"\tTEXT NOT NULL,\n" +
				"\tPRIMARY KEY(\"ID\")\n" +
				");";
		Update.executeQuery(query);
	}

	public static List<Prediction> getAllPredictions(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.PREDICTION + " " + condition;
		List<Prediction> predictions = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				predictions.add(new Prediction(
						rs.getString("ID"),
						rs.getString("EMAIL"),
						rs.getString("DATE")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(predictions);
		return predictions;
	}

	public static List<Prediction> getAllPredictions() {
		return getAllPredictions("");
	}

	public static List<Prediction> getPredictionsByEmail(String email) {
		return getAllPredictions("WHERE EMAIL='" + email + "'");
	}

	public static List<Prediction> getPredictionsByDate(String date) {
		return getAllPredictions("WHERE DATE='" + date + "'");
	}

	public static Prediction getPredictionById(String id) {
		return getAllPredictions("WHERE ID='" + id + "'").remove(0);
	}
}
