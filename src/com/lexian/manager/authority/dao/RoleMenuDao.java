package com.lexian.manager.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.RoleMenu;

public interface RoleMenuDao {
	
	public List<RoleMenu> getRoleMenus(int id);
	
	public void insertByBatch(List<RoleMenu> rms);
	
	public void deleteByBatch(@Param("roleId") int roleId,@Param("rms")List<RoleMenu> rms);
	
}
