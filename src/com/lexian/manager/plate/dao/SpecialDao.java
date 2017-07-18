package com.lexian.manager.plate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.plate.bean.Special;

public interface SpecialDao {

	public List<Special> getSpecialPage(Map<String, Object> params);
	
	public void updateSpecial(@Param("id")int id,@Param("name")String name);
	
	public void deleteSpecial(int id);
	
	public void addSpecial(String name);
	
	public Special getSpecialByName(String name);
	
	public int getCountSpecial();
}
