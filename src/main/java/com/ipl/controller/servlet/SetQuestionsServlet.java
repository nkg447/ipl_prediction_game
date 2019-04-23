package com.ipl.controller.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.form.QuestionsForm;
import com.ipl.model.entity.Predictor;
import com.ipl.service.SetQuestionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetQuestionsServlet extends HttpServlet {
	final static Logger logger = Logger.getLogger(SetQuestionsServlet.class);
	static private JsonParser PARSER = new JsonParser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null &&
				session.getAttribute("email").equals(Predictor.ADMIN_EMAIL)) {
			QuestionsForm form = getForm(request);
			JsonObject jsonObject = new JsonObject();
			try {
				SetQuestionService.setQuestions(form);
				responseData.setStatus(Response.SUCCESS);
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				responseData.setStatus(Response.FAILURE);
				responseData.setError(e.getMessage());
			}
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	private QuestionsForm getForm(HttpServletRequest request) throws IOException {
		String body = Util.getRequestBody(request);
		JsonObject jsonObject = (JsonObject) PARSER.parse(body);
		String date = jsonObject.get("date").getAsString();
		List<QuestionsForm.Question> questionList = new ArrayList<>();

		JsonArray questions = jsonObject.getAsJsonArray("questions");
		for (JsonElement question : questions) {
			Set<String> options = new HashSet<>();
			for (JsonElement option : question.getAsJsonObject().getAsJsonArray("options")) {
				options.add(option.getAsString());
			}
			questionList.add(new QuestionsForm.Question(
					question.getAsJsonObject().get("question").getAsString(),
					question.getAsJsonObject().get("type").getAsString(),
					options,
					question.getAsJsonObject().get("points").getAsInt()
			));
		}

		return new QuestionsForm(questionList, date);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
