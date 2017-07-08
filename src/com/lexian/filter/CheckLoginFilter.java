package com.lexian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter {

	private String targetPage;
	private String root;
	private String loginAction;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		targetPage = filterConfig.getInitParameter("target-page");
		loginAction = filterConfig.getServletContext().getInitParameter("login-action");
		root = filterConfig.getServletContext().getContextPath();

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (canContinue(req, resp)) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(root + "/" + targetPage);
		}
	}

	private boolean canContinue(HttpServletRequest req, HttpServletResponse resp) {
		boolean canContinue = true;

		if (!req.getRequestURI().toString().contains(loginAction)) {
			HttpSession session = req.getSession();
			if (session.getAttribute("managerId") == null) {
				canContinue = false;
			}
		}

		return canContinue;
	}

	@Override
	public void destroy() {

	}

}
