package com.ipl.dao;

import com.ipl.dao.sql.DatabaseInfo;
import com.ipl.dao.sql.Query;
import com.ipl.dao.sql.Update;
import com.ipl.model.entity.Prediction;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictionDAO {
	private final static Logger logger = Logger.getLogger(PredictionDAO.class);

	public static void save(Prediction prediction) {
		String query = "REPLACE INTO " + DatabaseInfo.PREDICTION
				+ "(EMAIL, DATE) VALUES(" +
				"'" + prediction.getEmail() + "'," +
				"'" + prediction.getDate() + "')";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.PREDICTION + "\" (\n" +
				"\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
				"\t\"EMAIL\"\tTEXT NOT NULL,\n" +
				"\t\"DATE\"\tTEXT NOT NULL\n" +
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
						rs.getInt("ID"),
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

	public static Prediction getPredictionsByDateAndEmail(String date, String email) {
		return getAllPredictions("WHERE DATE='" + date + "' AND EMAIL='" + email + "'").remove(0);
	}

	public static Prediction getPredictionById(String id) {
		return getAllPredictions("WHERE ID='" + id + "'").remove(0);
	}
}
