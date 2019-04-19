package com.ipl.controller.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.model.entity.Question;
import com.ipl.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionsServlet extends HttpServlet {

	static private JsonParser PARSER = new JsonParser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null) {
			JsonObject jsonObject = new JsonObject();
			JsonArray questionsArray = new JsonArray();

			List<Question> questions = Service.getQuestions(Util.todayDateString());
			questions.stream()
					.forEach(question -> {
						JsonObject object = new JsonObject();
						object.addProperty("id", question.getId());
						object.addProperty("question", question.getQuestion());
						object.add("options", (PARSER.parse(question.getOptions())));
						questionsArray.add(object);
					});

			jsonObject.add("questions", questionsArray);
			responseData.setStatus(Response.SUCCESS);
			responseData.setData(jsonObject);
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
