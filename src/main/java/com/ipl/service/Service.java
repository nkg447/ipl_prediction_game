package com.ipl.service;

import com.ipl.Util;
import com.ipl.controller.form.PredictionForm;
import com.ipl.controller.form.RegisterForm;
import com.ipl.dao.*;
import com.ipl.model.entity.*;

import java.util.List;

public class Service {
	public static void register(RegisterForm registerForm) {
		Predictor predictor = new Predictor(
				registerForm.getName(),
				0,
				0
		);
		Authentication authentication = new Authentication(
				predictor.getAuthenticationId(),
				registerForm.getEmail(),
				ServiceUtil.hashOf(registerForm.getPassword())
		);
		PredictorDAO.save(predictor);
		AuthenticationDAO.save(authentication);
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
	}
}
