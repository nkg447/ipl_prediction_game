package com.ipl.dao.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private final static Logger logger = Logger.getLogger(DatabaseConnection.class);
	private static Connection CONNECTION_INSTANCE;
	private static String DATABASE_URL =
			(System.getenv("JAWSDB_URL") != null) ?
					System.getenv("JAWSDB_URL") :
					"jdbc:mysql://localhost:3306/ipl?user=root";

	private static Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DATABASE_URL);
			logger.info("Connection to SQLite has been established.");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}

		return conn;
	}

	public static Connection getConnection() {
		if (CONNECTION_INSTANCE == null) {
			CONNECTION_INSTANCE = createConnection();
		}
		return CONNECTION_INSTANCE;
	}
}
