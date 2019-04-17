package com.ipl.service;

import com.ipl.controller.form.RegisterForm;
import com.ipl.dao.AuthenticationDAO;
import com.ipl.dao.PredictorDAO;
import com.ipl.model.entity.Authentication;
import com.ipl.model.entity.Predictor;

public class Service {
	public static void register(RegisterForm registerForm) {
		Predictor predictor = new Predictor(
				registerForm.getName(),
				true,
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
}
