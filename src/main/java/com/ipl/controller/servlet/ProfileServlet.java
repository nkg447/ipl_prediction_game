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
		HttpSession session = request.getSession(false);
		JsonObject jsonObject = new JsonObject();

		if (session != null) {
			String email = (String) session.getAttribute("email");

			jsonObject.addProperty("email", email);
		} else {
			jsonObject.addProperty("error", "login required");
		}
		response.getWriter().println(jsonObject);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
