package com.ipl.controller.servlet;

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

public class SetQuestionsServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(SetQuestionsServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null &&
				session.getAttribute("email").equals(Predictor.ADMIN_EMAIL)) {
			QuestionsForm form = new QuestionsForm();
			ServletUtil.getForm(request, form);

			try {
//				form.validate();
				SetQuestionService.setQuestions(form);
				responseData.setStatus(Response.SUCCESS);
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
