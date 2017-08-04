/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public class SessionContext{
	
	private static Map<Integer,HttpSession> sessions=new HashMap<>();
	private static SessionContext instance=new SessionContext();
	
	private SessionContext() {
		// TODO Auto-generated constructor stub
	}
	public static SessionContext getInstance() {
		return instance;
	}
	public void addSession(Integer managerId,HttpSession httpSession){
		sessions.put(managerId, httpSession);
	}
	public void deleteSession(Integer managerId){
		sessions.remove(managerId);
	}
	public HttpSession getSessionByManagerId(Integer managerId){
		return sessions.get(managerId);
	}
	
}
