package com.ipl.controller.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ipl.dao.PredictorDAO;
import com.ipl.model.entity.Predictor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class LeaderboardServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request,responseData)) != null) {
			JsonObject jsonObject = new JsonObject();
			JsonArray predictorsArray = new JsonArray();
			List<Predictor> predictors = PredictorDAO.getAllPredictors();

			predictors.stream()
					.sorted(Comparator.comparingInt(Predictor::getScore))
					.forEach(predictor -> {
						JsonObject object = new JsonObject();
						object.addProperty("name", predictor.getName());
						object.addProperty("score",predictor.getScore());
						predictorsArray.add(object);
					});

			jsonObject.add("leaderboard", predictorsArray);
			responseData.setData(jsonObject);
			responseData.setStatus(Response.SUCCESS);
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
