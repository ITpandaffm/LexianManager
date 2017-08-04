/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.plate.bean.SpecialCommodity;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SpeCommodityDao {

	public void deleteSpeCommodity(int id);
	
	public int getCountSpeCommodities(int id);
	
	public List<SpecialCommodity> getSpecialCommoditiesPage(Map<String, Object> params);
	
	public void addSpecialCommodities(@Param("commodityNo")String commodityNo,@Param("id")int id);

	public SpecialCommodity getSpecialCommodity(@Param("commodityNo")String commodityNo,@Param("specialId")int specialId);

}
