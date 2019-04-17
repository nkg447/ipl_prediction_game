package com.ipl.controller.data;

public class APIEndpoint {
	private static final String API_KEY = "YuHpkk7LdySRbI5mTfEMp6bAwzW2";
	private static final String BASE_URL = "https://cricapi.com";

	public static String matchSummary(String uid) {
		return BASE_URL + "/api/fantasySummary?apikey=" + API_KEY + "&unique_id=" + uid;
	}

	public static String playersInfo(String uid) {
		return BASE_URL + "/api/fantasySquad?apikey=" + API_KEY + "&unique_id=" + uid;
	}

	public static String newMatches() {
		return BASE_URL + "/api/matches?apikey=" + API_KEY;
	}
}
