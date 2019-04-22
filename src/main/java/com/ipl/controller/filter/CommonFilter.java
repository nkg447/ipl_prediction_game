package com.ipl.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class CommonFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(req, resp);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
