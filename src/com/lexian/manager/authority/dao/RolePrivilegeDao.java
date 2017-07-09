package com.lexian.manager.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.RolePrivilege;

public interface RolePrivilegeDao {
	
	public List<RolePrivilege> getRolePrivileges(int id);
	
	public void insertByBatch(List<RolePrivilege> rps);
	
	public void deleteByBatch(@Param("roleId") int roleId,@Param("rps")List<RolePrivilege> rps);
}
