package com.lexian.manager.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;
import com.lexian.web.ResultHelper;

public interface ManagerDao {
	
	public Manager getManagerByNameAndPassword(@Param("name")String name,@Param("password")String password);
	
	
	public List<Privilege> getPrivileges(int id);
	
	public Manager getUserWithMenus(int id);
	
	public void addManager(Manager manager);

	public void updateManager(Manager manager);
	
	public List<Manager> getManagers();

	public List<String> getPrivilegeUrls(int id);


	public void deleteManagerById(int id);


	public Integer verifyPassword(@Param("id")Integer id, @Param("password")String password);
}
