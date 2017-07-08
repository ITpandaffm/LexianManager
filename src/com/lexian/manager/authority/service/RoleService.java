package com.lexian.manager.authority.service;

import com.lexian.manager.authority.bean.Role;
import com.lexian.web.ResultHelper;

public interface RoleService {
	
	public ResultHelper getRoles();

	public ResultHelper addRole(Role role);

	public ResultHelper updateRole(Role role);

	public ResultHelper createMenusAndPrivileges();
	
	
}
