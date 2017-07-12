package com.lexian.manager.authority.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Privilege;

public interface ManagerDao {
	
	public Manager getManagerByNameAndPassword(@Param("name")String name,@Param("password")String password);
	
	
	public List<Privilege> getPrivilegesPage(Map<String, Object> params);
	
	public Manager getUserWithMenus(int id);
	
	public void addManager(Manager manager);

	public void updateManager(Manager manager);
	
	public List<Manager> getManagersPage(Map<String, Object> params);

	public List<String> getPrivilegeUrls(int id);


	public void deleteManagerById(int id);


	public Integer verifyPassword(@Param("id")Integer id, @Param("password")String password);
}
