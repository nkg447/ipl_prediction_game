package com.ipl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class Util {
	public static final ObjectMapper MAPPER = new ObjectMapper();
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final static Logger logger = Logger.getLogger(Util.class);
	private static final int DAY_DIFFERENCE = 0;

	public static String sendGet(String url) {
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String getRequestBody(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader br=new BufferedReader(request.getReader());
		String body = br.lines().collect(Collectors.joining());
		return body;
	}

	public static String todayDateString() {
		logger.debug("today date - " + getDateString(DAY_DIFFERENCE));
		return getDateString(DAY_DIFFERENCE);
	}

	public static String yesturdayDateString() {
		logger.debug("yesturday date - " + getDateString(-1 + DAY_DIFFERENCE));
		return getDateString(-1 + DAY_DIFFERENCE);
	}

	public static String getDateString(int day_difference) {
		return DATE_FORMAT.format(today(day_difference));
	}

	public static Date today(int day_difference) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day_difference);
		return cal.getTime();
	}

	public static String toStringOf(Object o) {
		try {
			return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return o.toString();
	}
}
