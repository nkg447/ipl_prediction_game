package com.ipl.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.service.data.APIEndpoint;
import com.ipl.service.data.MatchDetail;
import com.ipl.service.data.matchsummary.MatchSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class ServiceData extends TimerTask {

	private static Map<String, MatchDetail> matchDetailMap = new HashMap<>();
	private static Map<String, MatchSummary> matchSummaryMap = new HashMap<>();
	private static Map<String, String> dateToUniqueIdMap = new HashMap<>();
	private static JsonParser PARSER = new JsonParser();

	public static MatchDetail getMatchDetail(String date) {
		if (!matchDetailMap.containsKey(date)) {
			String uid = getUniqueId(date);
			MatchDetail matchDetail = (MatchDetail) urlToObject(APIEndpoint.matchDetails(uid), MatchDetail.class);
			matchDetailMap.put(date, matchDetail);
		}
		return matchDetailMap.get(date);
	}

	public static MatchSummary getMatchSummary(String date) {
		if (!matchSummaryMap.containsKey(date)) {
			String uid = getUniqueId(date);
			MatchSummary matchSummary = (MatchSummary) urlToObject(APIEndpoint.matchSummary(uid), MatchSummary.class);
			matchSummaryMap.put(date, matchSummary);
		}
		return matchSummaryMap.get(date);
	}

	private static String getUniqueId(String date) {
		if (!dateToUniqueIdMap.containsKey(date)) {
			String response = Util.sendGet(APIEndpoint.newMatches());
			JsonObject jsonObject = (JsonObject) PARSER.parse(response);
			JsonArray tempJsonArray = jsonObject.getAsJsonArray("matches");

			for (JsonElement object : tempJsonArray) {
				if ((object.getAsJsonObject().get("date").getAsString()).startsWith(date)) {
					dateToUniqueIdMap.put(date, object.getAsJsonObject().get("unique_id").getAsString());
					break;
				}
			}
		}
		return dateToUniqueIdMap.get(date);
	}

	private static Object urlToObject(String url, Class objectType) {
		return jsonToObject(Util.sendGet(url), objectType);
	}

	private static Object jsonToObject(String json, Class objectType) {
		try {
			return Util.MAPPER.readValue(json, objectType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {

	}
}
