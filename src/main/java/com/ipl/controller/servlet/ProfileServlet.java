package com.ipl.controller.servlet;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseData = new Response();
		HttpSession session;
		if ((session = ServletUtil.sessionAvailable(request, responseData)) != null) {
			JsonObject jsonObject = new JsonObject();

			String email = (String) session.getAttribute("email");
			jsonObject.addProperty("email", email);

			responseData.setData(jsonObject);
			responseData.setStatus(Response.SUCCESS);
		}
		response.getWriter().println(responseData.toJsonObject());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
