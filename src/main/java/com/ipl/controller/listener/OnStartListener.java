package com.ipl.controller.listener;

import com.ipl.dao.*;
import com.ipl.dao.sql.DatabaseConnection;
import com.ipl.service.ServiceData;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.SQLException;

public class OnStartListener implements ServletContextListener,
		HttpSessionListener, HttpSessionAttributeListener {

	// Public constructor is required by servlet spec
	public OnStartListener() {
	}

	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context=sce.getServletContext();
		createDatabaseConnection(context);
		createTables();
		new ServiceData().run();
	}

	private void createTables() {
		PredictionDAO.createTable();
		PredictorDAO.createTable();
		AnswerDAO.createTable();
		QuestionDAO.createTable();
		AuthenticationDAO.createTable();
	}

	private void createDatabaseConnection(ServletContext context) {
		String db_path = context.getInitParameter("db_path");
		db_path =context.getRealPath(db_path).replace("\\", "/");
		DatabaseConnection.getConnection(db_path);
	}

	public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
		try {
			DatabaseConnection.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------
	// HttpSessionListener implementation
	// -------------------------------------------------------
	public void sessionCreated(HttpSessionEvent se) {
		/* Session is created. */
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		/* Session is destroyed. */
	}

	// -------------------------------------------------------
	// HttpSessionAttributeListener implementation
	// -------------------------------------------------------

	public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
	}

	public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
	}

	public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
	}
}
