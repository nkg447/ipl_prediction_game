package com.ipl.model.entity;

import com.ipl.dao.PredictorDAO;

public class Prediction {
	private int id;
	private Predictor predictor;
	private String date;

	public Prediction(int predictorId) {
		this(predictorId, "");
	}

	public Prediction(int predictorId, String date) {
		this(0, predictorId, date);
	}

	public Prediction(int id, int predictorId, String date) {
		this.id = id;
		this.predictor = PredictorDAO.getPredictorById(predictorId);
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public Predictor getPredictor() {
		return predictor;
	}

	public String getDate() {
		return date;
	}
}
