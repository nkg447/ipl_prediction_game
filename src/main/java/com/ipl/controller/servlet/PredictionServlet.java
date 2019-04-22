package com.ipl.controller.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.PredictionForm;
import com.ipl.service.Service;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredictionServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(PredictionServlet.class);
	static private JsonParser PARSER = new JsonParser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null) {
			JsonObject jsonObject = new JsonObject();
			PredictionForm form = getForm(request);
			try {
				Service.predict(form, (String) session.getAttribute("email"));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				responseData.setError(e.getMessage());
			}
			responseData.setStatus(Response.SUCCESS);
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	private PredictionForm getForm(HttpServletRequest request) throws IOException {
		String body = Util.getRequestBody(request);
		JsonArray jsonElements = (JsonArray) PARSER.parse(body);

		List<PredictionForm.Prediction> predictions = new ArrayList<>();

		for (JsonElement je : jsonElements) {
			String answer = "";
			if (je.getAsJsonObject().get("answer").isJsonArray()) {
				answer = je.getAsJsonObject().get("answer").getAsJsonArray().toString();
			} else
				answer = je.getAsJsonObject().get("answer").getAsString();
			predictions.add(new PredictionForm.Prediction(
					je.getAsJsonObject().get("id").getAsInt(),
					answer
			));
			logger.debug(answer);
		}
		return new PredictionForm(predictions);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
