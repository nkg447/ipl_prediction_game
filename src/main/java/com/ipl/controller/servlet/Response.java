package com.ipl.controller.servlet;

import com.google.gson.JsonObject;

public class Response {
	private String status;
	private JsonObject data;
	private JsonObject error;

	static final String SUCCESS="SUCCESS";
	static final String FAILURE="FAILURE";

	public Response(){
		this(null, null, null);
	}

	public Response(String status, JsonObject data, JsonObject error) {
		this.status = status;
		this.data = data;
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}

	public JsonObject getError() {
		return error;
	}

	public void setError(JsonObject error) {
		this.error = error;
	}

	JsonObject toJsonObject(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", status);
		jsonObject.add("data", data);
		jsonObject.add("error", error);
		return jsonObject;
	}

	public void setError(String message) {
		JsonObject error = new JsonObject();
		error.addProperty("message", message);
		setError(error);
	}
}
