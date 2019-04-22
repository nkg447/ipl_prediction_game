package com.ipl.service;

import com.ipl.dao.AuthenticationDAO;
import com.ipl.dao.PredictorDAO;
import com.ipl.form.RegisterForm;
import com.ipl.model.entity.Authentication;
import com.ipl.model.entity.Predictor;

public class RegisterService {
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
}
