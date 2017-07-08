package com.lexian.manager.authority.service;

import com.lexian.web.ResultHelper;


public interface ManagerService {

	public ResultHelper signIn(String name,String password);
	
	public ResultHelper updatePasswordById(int id,String newPassword);
	
	public ResultHelper getPrivileges(int id);
	
	public ResultHelper getMenus(int id);
	
	
	
}
