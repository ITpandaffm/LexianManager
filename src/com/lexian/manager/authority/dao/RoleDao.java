package com.lexian.manager.authority.dao;

import java.util.List;

import com.lexian.manager.authority.bean.Role;

public interface RoleDao {
	
	public List<Role> getRoles();

	public Role addRole(Role role);

	public void updateRole(Role role);
}
