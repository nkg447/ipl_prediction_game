package com.ipl.service;

import com.ipl.Util;
import com.ipl.dao.*;
import com.ipl.form.PredictionForm;
import com.ipl.form.RegisterForm;
import com.ipl.model.entity.*;

import java.util.List;

public class Service {
	public static void register(RegisterForm registerForm) throws Exception {
		Authentication authentication = new Authentication(
				registerForm.getEmail(),
				ServiceUtil.hashOf(registerForm.getPassword())
		);
		AuthenticationDAO.save(authentication);
		authentication = AuthenticationDAO.getAuthenticationByEmail(registerForm.getEmail());
		Predictor predictor = new Predictor(
				authentication.getId(),
				registerForm.getName()
		);
		PredictorDAO.save(predictor);
	}

	public static boolean authenticate(String email, String password) {
		Authentication authentication = AuthenticationDAO.getAuthenticationByEmail(email);
		String userPass = ServiceUtil.hashOf(password);
		return userPass.equals(authentication.getPassword());
	}

	public static List<Question> getQuestions(String date) {
		List<Question> questions = QuestionDAO.getQuestionsByDate(date);
		return questions;
	}

	public static void predict(PredictionForm form, String email) throws Exception {
		Predictor predictor = PredictorDAO.getPredictorByEmail(email);
		Prediction prediction = new Prediction(
				predictor.getId(),
				Util.todayDateString()
		);
		PredictionDAO.save(prediction);
		prediction = PredictionDAO.getPredictionsByDateAndPredictionId(Util.todayDateString(), prediction.getId());
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
