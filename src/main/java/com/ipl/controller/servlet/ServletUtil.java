package com.ipl.controller.servlet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.Form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletUtil {
	static private JsonParser PARSER = new JsonParser();

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

	public static <T extends Form> void getForm(HttpServletRequest request, T form) throws IOException {
		String body = Util.getRequestBody(request);
		JsonElement jsonBody = PARSER.parse(body);
		form.populate(jsonBody);
	}
}
