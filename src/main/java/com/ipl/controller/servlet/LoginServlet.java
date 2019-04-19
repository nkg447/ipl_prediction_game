package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = new LoginForm(request.getParameter("email"),
				request.getParameter("password"));
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
			jsonObject.addProperty("error", "invalid dto");
		} catch (Exception e) {
			jsonObject.addProperty("error", e.getMessage());
		}
		jsonObject.addProperty("authenticated", authenticated);
		response.getWriter().println(jsonObject);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
