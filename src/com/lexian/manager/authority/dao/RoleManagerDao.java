package com.lexian.manager.authority.dao;

import java.util.List;

import com.lexian.manager.authority.bean.RoleManager;

public interface RoleManagerDao {
	
	public void insertRoleManagerBatch(List<RoleManager> rms);
	public void deleteRoleManagerByManagerId(Integer managerId);
	
}
