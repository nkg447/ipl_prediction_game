package com.ipl.service;

import com.ipl.Util;
import com.ipl.dao.AnswerDAO;
import com.ipl.dao.PredictionDAO;
import com.ipl.dao.PredictorDAO;
import com.ipl.form.PredictionForm;
import com.ipl.model.entity.Answer;
import com.ipl.model.entity.Prediction;
import com.ipl.model.entity.Predictor;

public class PredictService {
	public static void predict(PredictionForm form, String email) throws Exception {
		Predictor predictor = PredictorDAO.getPredictorByEmail(email);
		Prediction prediction = new Prediction(
				predictor.getId(),
				Util.todayDateString()
		);
		PredictionDAO.save(prediction);
		prediction = PredictionDAO.getPredictionsByDateAndPredictorId(Util.todayDateString(), predictor.getId());
		int pid = prediction.getId();
		for (PredictionForm.Prediction p : form.getPredictions()) {
			Answer answer = new Answer(
					pid,
					p.getAnswer(),
					p.getQuestionId()
			);
			AnswerDAO.save(answer);
		}
		if (email.equals(Predictor.ADMIN_EMAIL)) {
			ServiceData.updateScores(Util.todayDateString());
		}
	}
}
