package com.lexian.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.lexian.context.SessionContext;

public class SessionListener implements HttpSessionListener{
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		HttpSession session = se.getSession();
		
		Integer managerId=(Integer) session.getAttribute("managerId");
		if(managerId!=null){
			SessionContext.getInstance().deleteSession(managerId);
		}
		HttpSessionListener.super.sessionDestroyed(se);
	}
}
