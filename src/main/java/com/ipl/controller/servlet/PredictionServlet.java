package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.ipl.controller.form.PredictionForm;
import com.ipl.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PredictionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		JsonObject jsonObject = new JsonObject();

		if (session != null) {
			PredictionForm form = getForm(request);
			Service.predict(form, (String) session.getAttribute("email"));
			jsonObject.addProperty("success", true);
		} else {
			jsonObject.addProperty("error", "login required");
		}
		response.getWriter().println(jsonObject);
	}

	private PredictionForm getForm(HttpServletRequest request) {
		// TODO request to PredictionForm
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
