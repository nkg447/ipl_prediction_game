package com.ipl.controller.servlet;

import com.google.gson.JsonObject;
import com.ipl.Util;
import com.ipl.model.entity.Predictor;
import com.ipl.service.ServiceData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MatchServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null &&
				session.getAttribute("email").equals(Predictor.ADMIN_EMAIL)) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("players", ServiceData.playerDetails(Util.todayDateString()));
			jsonObject.add("teams", ServiceData.teamNames(Util.todayDateString()));
			responseData.setStatus(Response.SUCCESS);
			responseData.setData(jsonObject);
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
