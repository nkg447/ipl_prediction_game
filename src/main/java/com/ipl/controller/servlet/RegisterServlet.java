package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.ipl.controller.form.RegisterForm;
import com.ipl.controller.form.ValidationException;
import com.ipl.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(RegisterServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm registerForm = new RegisterForm(
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password")
		);

		JsonObject jsonObject = new JsonObject();
		try {
			registerForm.validate();
			Service.register(registerForm);
			jsonObject.addProperty("email", registerForm.getEmail());
			jsonObject.addProperty("registered", true);

		} catch (ValidationException e) {
			jsonObject.addProperty("error", "invalid data");
		}
		response.getWriter().println(jsonObject);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
