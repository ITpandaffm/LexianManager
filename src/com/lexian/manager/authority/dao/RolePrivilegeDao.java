/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.RolePrivilege;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface RolePrivilegeDao {
	
	public List<RolePrivilege> getRolePrivileges(int id);
	
	public void insertByBatch(List<RolePrivilege> rps);
	
	public void deleteByBatch(@Param("roleId") int roleId,@Param("rps")List<RolePrivilege> rps);
}
