package com.ipl.service;

import com.ipl.dao.AuthenticationDAO;
import com.ipl.model.entity.Authentication;

public class AuthService {
	public static boolean authenticate(String email, String password) {
		Authentication authentication = AuthenticationDAO.getAuthenticationByEmail(email);
		String userPass = ServiceUtil.hashOf(password);
		return userPass.equals(authentication.getPassword());
	}
}
