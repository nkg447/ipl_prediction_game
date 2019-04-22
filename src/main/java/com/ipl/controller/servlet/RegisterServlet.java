package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.RegisterForm;
import com.ipl.form.ValidationException;
import com.ipl.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(RegisterServlet.class);
	static private JsonParser PARSER = new JsonParser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm registerForm = getForm(request);
		Response responseDate = new Response();
		JsonObject jsonObject = new JsonObject();
		try {
			registerForm.validate();
			Service.register(registerForm);
			jsonObject.addProperty("email", registerForm.getEmail());
			jsonObject.addProperty("registered", true);
			responseDate.setData(jsonObject);
		} catch (ValidationException e) {
			responseDate.setError(e.getMessage());
		} catch (Exception e) {
			responseDate.setError(e.getMessage());
		}
		response.getWriter().println(responseDate.toJsonObject());
	}

	private RegisterForm getForm(HttpServletRequest request) throws IOException {
		String body = Util.getRequestBody(request);
		JsonObject jsonObject = (JsonObject) PARSER.parse(body);

		return new RegisterForm(
				jsonObject.get("name").getAsString(),
				jsonObject.get("email").getAsString(),
				jsonObject.get("password").getAsString()
		);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
