package com.ipl.dao;

import com.ipl.dao.util.DatabaseConnection;
import com.ipl.dao.util.DatabaseInfo;
import com.ipl.dao.util.Query;
import com.ipl.dao.util.Update;
import com.ipl.model.entity.Authentication;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationDAO {

	private final static Logger logger = Logger.getLogger(AuthenticationDAO.class);

	public static void save(Authentication authentication) throws SQLException {
		PreparedStatement preparedStatement =
				DatabaseConnection.getConnection().prepareStatement(
						"insert into authentication (EMAIL, PASSWORD) values (?, ?)"
				);
		preparedStatement.setString(1, authentication.getEmail());
		preparedStatement.setString(2, authentication.getPassword());
		Update.executeQuery(preparedStatement);
	}

	public static List<Authentication> getAllAuthentications(String condition) {
		String query = "SELECT * FROM " + DatabaseInfo.AUTHENTICATION + " " + condition;
		List<Authentication> authentications = new ArrayList<>();
		try {
			ResultSet rs = Query.executeQuery(query);

			while (rs.next()) {
				authentications.add(new Authentication(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("tlm"),
						rs.getString("whenCreated")
				));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return authentications;
	}

	public static List<Authentication> getAllAuthentications() {
		return getAllAuthentications("");
	}

	public static Authentication getAuthenticationByEmail(String email) {
		return getAllAuthentications("WHERE EMAIL='" + email + "'").remove(0);
	}

	public static Authentication getAuthenticationById(int id) {
		return getAllAuthentications("WHERE ID=" + id).remove(0);
	}
}
