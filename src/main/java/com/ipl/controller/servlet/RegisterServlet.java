package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.ipl.form.RegisterForm;
import com.ipl.form.ValidationException;
import com.ipl.service.RegisterService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(RegisterServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm registerForm = ServletUtil.getForm(request, new RegisterForm());
		Response responseData = new Response();
		JsonObject jsonObject = new JsonObject();
		try {
			registerForm.validate();
			RegisterService.register(registerForm);
			jsonObject.addProperty("email", registerForm.getEmail());
			jsonObject.addProperty("registered", true);
			responseData.setData(jsonObject);
			responseData.setStatus(Response.SUCCESS);
		} catch (ValidationException e) {
			responseData.setError(e.getEntity() + " invalid");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setError(e.getMessage());
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
