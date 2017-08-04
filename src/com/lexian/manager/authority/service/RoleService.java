/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Role;
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
public interface RoleService {
	
	public ResultHelper getRoles(Page page);

	public ResultHelper addRole(Role role);

	public ResultHelper updateRole(Role role);

	
	public ResultHelper getMenus(Integer id);

	public ResultHelper updateMenus(Integer id, int[] menuIds);

	public ResultHelper updatePrivileges(Integer id, int[] privilegeId);

	public ResultHelper getPrivileges(Integer id);

	public ResultHelper getAllRoles();
	
	public ResultHelper getRoleByManagerId(Integer managerId);
}
