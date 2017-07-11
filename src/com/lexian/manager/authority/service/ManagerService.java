package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.web.ResultHelper;


public interface ManagerService {

	public ResultHelper signIn(String name,String password);
	
	public ResultHelper getPrivileges(int id);
	
	public ResultHelper getPrivilegeUrls(int id);
	
	public ResultHelper getUserWithMenus(int id);
	
	public ResultHelper addManager(Manager manager);
	
	public ResultHelper updateManager(Manager manager);
	
	public ResultHelper associateToRole(RoleManager rl);

	public ResultHelper getManagers();

	public ResultHelper deleteManagerById(int id);

	public ResultHelper verifyPassword(Integer id, String password);

	public ResultHelper updateManagerPassword(Manager manager, String newPass);
	
	
	
}
