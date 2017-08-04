/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
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

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class PageFilter implements Filter {

	private String homePage;
	private String root;
	private String loginPage;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		homePage = filterConfig.getInitParameter("home-page");
		
		loginPage=filterConfig.getInitParameter("login-page");
		root = filterConfig.getServletContext().getContextPath();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		
		String url=req.getRequestURI().toString();
		
		if(url.equals(root+"/")||url.contains(loginPage)){
			if(isLogin(req, resp)){
				resp.sendRedirect(root + "/" + homePage);
				return;
			}
		}else if(url.contains(homePage)){
			if(!isLogin(req, resp)){
				resp.sendRedirect(root + "/" + loginPage);
				return ;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isLogin(HttpServletRequest req, HttpServletResponse resp) {
		boolean goHome=true;
		HttpSession session = req.getSession();
		if (session.getAttribute("managerId") == null) {
			goHome = false;
		}
		
		return goHome;
	}


	@Override
	public void destroy() {

	}

}
