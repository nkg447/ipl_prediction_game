package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.ipl.form.LoginForm;
import com.ipl.form.ValidationException;
import com.ipl.model.entity.Predictor;
import com.ipl.service.AuthService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(LoginServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = ServletUtil.getForm(request, new LoginForm());
		Response responseData = new Response();
		JsonObject jsonObject = new JsonObject();
		boolean authenticated = false;
		try {
			form.validate();
			authenticated = AuthService.authenticate(form.getEmail(), form.getPassword());
			if (authenticated) {
				HttpSession session = request.getSession(true);
				session.setAttribute("email", form.getEmail());
			}
		} catch (ValidationException e) {
			responseData.setError(e.getEntity() + " invalid");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setError(e.getMessage());
		}
		jsonObject.addProperty("authenticated", authenticated);
		jsonObject.addProperty("admin", form.getEmail().equals(Predictor.ADMIN_EMAIL));
		responseData.setData(jsonObject);
		responseData.setStatus(Response.SUCCESS);
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
