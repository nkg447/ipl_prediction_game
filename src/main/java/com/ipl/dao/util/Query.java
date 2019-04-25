package com.ipl.dao.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	private final static Logger logger = Logger.getLogger(Query.class);

	public static ResultSet execute(String query) throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		Statement statement = connection.createStatement();
		logger.info("executing query - " + query);
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}

	public static ResultSet executeQuery(String query) throws SQLException {
		try {
			return Query.execute(query);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
