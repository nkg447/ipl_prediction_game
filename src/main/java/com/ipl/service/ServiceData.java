package com.ipl.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ipl.Util;
import com.ipl.dao.QuestionDAO;
import com.ipl.model.entity.Question;
import com.ipl.service.data.APIEndpoint;
import com.ipl.service.data.MatchDetail;
import com.ipl.service.data.Player;
import com.ipl.service.data.Team;
import com.ipl.service.data.matchsummary.MatchSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Stream;

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

	public static String playerDetailsJson(String date) {
		MatchDetail matchDetail = ServiceData.getMatchDetail(date);
		JsonArray players = new JsonArray();
		Stream.concat(matchDetail.getSquad().get(0).getPlayers().stream(),
				matchDetail.getSquad().get(1).getPlayers().stream())
				.map(Player::getName)
				.sorted()
				.forEach(players::add);

		return players.toString();
	}

	public static String teamNamesJson(String date) {
		MatchDetail matchDetail = ServiceData.getMatchDetail(date);
		JsonArray teams = new JsonArray();
		matchDetail.getSquad().stream()
				.map(Team::getName)
				.forEach(teams::add);
		return teams.toString();
	}

	public static void addQuestions() {
		String playersOption = playerDetailsJson(Util.todayDateString());
		QuestionDAO.save(new Question(
				0,
				"Player to make most runs",
				Util.todayDateString(),
				playersOption,
				"MULTIPLE _CHOICE",
				10
		));
		QuestionDAO.save(new Question(
				0,
				"Player to make most wickets",
				Util.todayDateString(),
				playersOption,
				"MULTIPLE _CHOICE",
				10
		));
		QuestionDAO.save(new Question(
				0,
				"Man of the Match",
				Util.todayDateString(),
				playersOption,
				"MULTIPLE _CHOICE",
				10
		));
	}

	@Override
	public void run() {
		addQuestions();
	}
}
