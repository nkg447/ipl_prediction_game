package com.ipl.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CommonFilter implements Filter {
	final static Logger logger = Logger.getLogger(CommonFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		try {
			chain.doFilter(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
