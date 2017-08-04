/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.manager.authority.bean.Role;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface RoleDao {
	
	public List<Role> getRolesPage(Map<String,Object> params);

	public void addRole(Role role);

	public void updateRole(Role role);

	public List<Menu> getMenus(@Param("id")Integer id);
	
	public List<Privilege> getPrivileges(@Param("id")Integer id);
	
	public Integer hasNameUsed(String name);

	public List<Role> getAllRoles();
	
	public List<Role> getRoleByManagerId(Integer managerId);

}
