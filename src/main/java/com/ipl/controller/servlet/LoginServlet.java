package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.LoginForm;
import com.ipl.form.ValidationException;
import com.ipl.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	static private JsonParser PARSER = new JsonParser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = getForm(request);
		Response responseData = new Response();
		JsonObject jsonObject = new JsonObject();
		boolean authenticated = false;
		try {
			form.validate();
			authenticated = Service.authenticate(form.getEmail(), form.getPassword());
			if (authenticated) {
				HttpSession session = request.getSession(true);
				session.setAttribute("email", form.getEmail());
			}
		} catch (ValidationException e) {
			responseData.setError("invalid dto");
		} catch (Exception e) {
			responseData.setError(e.getMessage());
		}
		jsonObject.addProperty("authenticated", authenticated);
		responseData.setData(jsonObject);
		response.getWriter().println(responseData.toJsonObject());
	}

	private LoginForm getForm(HttpServletRequest request) throws IOException {
		String body = Util.getRequestBody(request);
		JsonObject jsonObject = (JsonObject) PARSER.parse(body);

		return new LoginForm(
				jsonObject.get("email").getAsString(),
				jsonObject.get("password").getAsString()
		);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
