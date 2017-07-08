package com.lexian.manager.authority.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.authority.bean.Manager;
import com.lexian.manager.authority.bean.Menu;
import com.lexian.manager.authority.bean.Privilege;

public interface ManagerDao {
	
	public Manager getManagerByNameAndPassword(@Param("name")String name,@Param("password")String password);
	
	public void updatePasswordById(@Param("id")int id,@Param("password")String newPassword);
	
	public List<Manager> getPrivileges(int id);
	
	public List<Menu> getMenus(int id);
	
	public Integer addManager(Manager manager);

	public void updateManagerInfo(Manager manager);
	
	public List<Manager> getManagers();
}
