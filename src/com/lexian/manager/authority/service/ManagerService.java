package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;


public interface ManagerService {

	public ResultHelper signIn(String name,String password);
	
	public ResultHelper getPrivileges(Integer id,Page page);
	
	public ResultHelper getPrivilegeUrls(Integer id);
	
	public ResultHelper getUserWithMenus(Integer id);
	
	public ResultHelper addManager(Manager manager,Integer[] roleId);
	
	public ResultHelper updateManager(Manager manager);

	public ResultHelper getManagers(Page page);

	public ResultHelper deleteManagerById(Integer id);

	public ResultHelper verifyPassword(Integer id, String password);

	public ResultHelper updateManagerPassword(Manager manager, String newPass);

	public ResultHelper getMenus(Integer id,Page page);
	
	public ResultHelper updateAssociatedRole(Manager manager,Integer[] newRoleId);

	
}
