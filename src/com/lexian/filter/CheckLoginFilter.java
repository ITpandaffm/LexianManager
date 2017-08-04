/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lexian.context.SessionContext;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class CheckLoginFilter implements Filter {

	private String loginAction;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginAction = filterConfig.getServletContext().getInitParameter("login-action");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (canContinue(req, resp)) {
			chain.doFilter(request, response);
			if(req.getRequestURI().toString().contains(loginAction)){
				avoidRepeatLogin(req);
			}
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			String jsonStr = "{\"code\":\"-101\",\"data\":\"请先登录\"}";
			PrintWriter out = null;
			try {
			    out = response.getWriter();
			    out.write(jsonStr);
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
		}
	}

	private void avoidRepeatLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
	
		Integer managerId=(Integer) session.getAttribute("managerId");
		if ( managerId!= null) {
			HttpSession otherSession=SessionContext.getInstance().getSessionByManagerId(managerId);
			if(otherSession!=null&&!session.equals(otherSession)){
				otherSession.invalidate();
			}
			SessionContext.getInstance().addSession(managerId, session);
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
