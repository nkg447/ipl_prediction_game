package com.ipl.dao;

import com.ipl.dao.util.DatabaseConnection;
import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Prediction;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredictionDAO {
	private final static Logger logger = Logger.getLogger(PredictionDAO.class);

	public static void save(Prediction prediction) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"insert into prediction (predictor_id) values (?)"
				);
		preparedStatement.setInt(1, prediction.getPredictor().getId());
		Update.executeQuery(preparedStatement);
	}

	public static List<Prediction> getAllPredictions(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.PREDICTION + " " + condition;
		List<Prediction> predictions = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				predictions.add(new Prediction(
						rs.getInt("id"),
						rs.getInt("predictor_id"),
						rs.getString("date")
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

	public static List<Prediction> getPredictionsByDate(String date) {
		return getAllPredictions("WHERE DATE LIKE '" + date + "%'");
	}

	public static Prediction getPredictionById(int id) {
		return getAllPredictions("WHERE ID=" + id).remove(0);
	}

	public static Prediction getPredictionsByDateAndPredictorId(String date, int id) {
		return getAllPredictions("WHERE date LIKE '" + date + "%' and predictor_id=" + id).remove(0);
	}
}
