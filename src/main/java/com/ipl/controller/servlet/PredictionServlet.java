package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.form.PredictionForm;
import com.ipl.form.ValidationException;
import com.ipl.service.PredictService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PredictionServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(PredictionServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null) {
			PredictionForm form = ServletUtil.getForm(request, new PredictionForm());
			try {
				form.validate();
				PredictService.predict(form, (String) session.getAttribute("email"));
				responseData.setStatus(Response.SUCCESS);
			} catch (ValidationException e) {
				responseData.setError(e.getEntity() + " invalid");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				responseData.setError(e.getMessage());
			}
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
