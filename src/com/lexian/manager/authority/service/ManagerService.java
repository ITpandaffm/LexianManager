/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
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
