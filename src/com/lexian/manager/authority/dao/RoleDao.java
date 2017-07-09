package com.lexian.manager.authority.dao;

import java.util.List;

import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.bean.Role;

public interface RoleDao {
	
	public List<Role> getRoles();

	public void addRole(Role role);

	public void updateRole(Role role);

	public List<Menu> getMenus();
	
	public List<Menu> getMenusById(Integer id);

	public List<Menu> getPrivilegesById(Integer id);
	
	public List<Privilege> getPrivileges();

	public List<Privilege> getPrivilegesById(int id);
}
