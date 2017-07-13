package com.lexian.manager.authority.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.bean.Role;

public interface RoleDao {
	
	public List<Role> getRolesPage(Map<String,Object> params);

	public void addRole(Role role);

	public void updateRole(Role role);

	public List<Menu> getMenus(@Param("id")Integer id);
	
	public List<Privilege> getPrivileges(@Param("id")Integer id);
	
	public Integer hasNameUsed(String name);

}
