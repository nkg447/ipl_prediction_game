package com.ipl.controller.servlet;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletUtil {
	static HttpSession sessionAvailable(HttpServletRequest request, Response responseData) throws IOException {
		HttpSession session = request.getSession(false);
		JsonObject jsonObject = new JsonObject();

		if (session == null) {
			jsonObject.addProperty("message", "login required");
			responseData.setStatus(Response.FAILURE);
			responseData.setError(jsonObject);
		}
		return session;
	}
}
