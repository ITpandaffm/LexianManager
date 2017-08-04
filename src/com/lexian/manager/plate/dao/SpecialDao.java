/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.plate.bean.Special;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SpecialDao {

	public List<Special> getSpecialPage(Map<String, Object> params);
	
	public void updateSpecial(@Param("id")int id,@Param("name")String name);
	
	public void deleteSpecial(int id);
	
	public void addSpecial(String name);
	
	public Special getSpecialByName(String name);
	
	public int getCountSpecial();
}
