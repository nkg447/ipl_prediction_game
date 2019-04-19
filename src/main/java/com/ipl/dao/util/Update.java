package com.ipl.dao.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
	private final static Logger logger = Logger.getLogger(Query.class);

	public static void execute(final String query) throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		Statement statement = connection.createStatement();
		logger.info("executing query - " + query);
		statement.execute(query);
	}

	public static void executeQuery(String query) {
		try {
			Update.execute(query);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void executeQuery(PreparedStatement preparedStatement) throws SQLException {
		logger.info("executing - " + preparedStatement);
		int i = preparedStatement.executeUpdate();
		logger.info(i + " records inserted");
	}
}
