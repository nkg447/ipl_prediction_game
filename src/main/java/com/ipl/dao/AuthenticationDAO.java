package com.ipl.dao;

import com.ipl.model.entity.Authentication;
import com.ipl.dao.sql.DatabaseInfo;
import com.ipl.dao.sql.Query;
import com.ipl.dao.sql.Update;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationDAO {

	private final static Logger logger = Logger.getLogger(AuthenticationDAO.class);

	public static void save(Authentication authentication) {
		String query = "INSERT INTO " + DatabaseInfo.AUTHENTICATION + " VALUES(" +
				"'" + authentication.getId() + "'," +
				"'" + authentication.getEmail() + "'," +
				"'" + authentication.getPassword() + "')";
		Update.executeQuery(query);
	}

	public static void createTable() {
		String query = "CREATE TABLE \"" + DatabaseInfo.AUTHENTICATION + "\" (\n" +
				"\t\"ID\"\tTEXT NOT NULL UNIQUE,\n" +
				"\t\"EMAIL\"\tTEXT NOT NULL UNIQUE,\n" +
				"\t\"PASSWORD\"\tTEXT NOT NULL,\n" +
				"\tPRIMARY KEY(\"ID\")\n" +
				");";
		Update.executeQuery(query);
	}

	public static List<Authentication> getAllAuthentications(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.AUTHENTICATION + " " + condition;
		List<Authentication> authentications = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				authentications.add(new Authentication(
						rs.getString("ID"),
						rs.getString("EMAIL"),
						rs.getString("PASSWORD")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info(authentications);
		return authentications;
	}

	public static List<Authentication> getAllAuthentications() {
		return getAllAuthentications("");
	}

	public static Authentication getAuthenticationByEmail(String email) {
		return getAllAuthentications("WHERE EMAIL='" + email + "'").remove(0);
	}
}
