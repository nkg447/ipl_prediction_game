package com.ipl.service;

import com.ipl.Util;
import com.ipl.dao.*;
import com.ipl.form.PredictionForm;
import com.ipl.form.RegisterForm;
import com.ipl.model.entity.*;

import java.util.List;

public class Service {
	public static void register(RegisterForm registerForm) {
		Authentication authentication = new Authentication(
				0,
				registerForm.getEmail(),
				ServiceUtil.hashOf(registerForm.getPassword())
		);
		AuthenticationDAO.save(authentication);
		authentication = AuthenticationDAO.getAuthenticationByEmail(registerForm.getEmail());
		Predictor predictor = new Predictor(
				registerForm.getName(),
				authentication.getId(),
				0
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

	public static void predict(PredictionForm form, String email) {
		Prediction prediction = new Prediction(
				0,
				email,
				Util.todayDateString()
		);
		PredictionDAO.save(prediction);
		prediction = PredictionDAO.getPredictionsByDateAndEmail(Util.todayDateString(), email);
		int pid = prediction.getId();
		form.getPredictions().stream()
				.map((p) -> new Answer(
						0,
						pid,
						p.getAnswer(),
						p.getQuestionId()
				))
				.forEach(AnswerDAO::save);
		if (email.equals(Predictor.ADMIN_EMAIL)) {
			ServiceData.updateScores(Util.todayDateString());
		}
	}
}
