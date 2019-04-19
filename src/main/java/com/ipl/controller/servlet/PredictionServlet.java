package com.ipl.controller.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.PredictionForm;
import com.ipl.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredictionServlet extends HttpServlet {

	static private JsonParser PARSER = new JsonParser();

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

	private PredictionForm getForm(HttpServletRequest request) throws IOException {
		String body = Util.getRequestBody(request);
		JsonArray jsonElements = (JsonArray) PARSER.parse(body);

		List<PredictionForm.Prediction> predictions = new ArrayList<>();

		for(JsonElement je : jsonElements){
			predictions.add(new PredictionForm.Prediction(
					je.getAsJsonObject().get("id").getAsInt(),
					je.getAsJsonObject().get("answer").getAsString()
			));
		}
		return new PredictionForm(predictions);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
