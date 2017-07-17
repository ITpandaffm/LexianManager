package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.RoleManager;
import com.lexian.web.ResultHelper;


public interface ManagerService {

	public ResultHelper signIn(String name,String password);
	
	public ResultHelper getPrivileges(Integer id,Integer pageNo);
	
	public ResultHelper getPrivilegeUrls(Integer id);
	
	public ResultHelper getUserWithMenus(Integer id);
	
	public ResultHelper addManager(Manager manager,Integer[] roleId);
	
	public ResultHelper updateManager(Manager manager);

	public ResultHelper getManagers(Integer pageNo);

	public ResultHelper deleteManagerById(Integer id);

	public ResultHelper verifyPassword(Integer id, String password);

	public ResultHelper updateManagerPassword(Manager manager, String newPass);

	public ResultHelper getMenus(Integer id,Integer pageNo);
	
	public ResultHelper updateAssociatedRole(Manager manager,Integer[] newRoleId);

	
}
