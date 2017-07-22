package com.lexian.manager.plate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.plate.bean.SpecialCommodity;

public interface SpeCommodityDao {

	public void deleteSpeCommodity(int id);
	
	public int getCountSpeCommodities(int id);
	
	public List<SpecialCommodity> getSpecialCommoditiesPage(Map<String, Object> params);
	
	public void addSpecialCommodities(@Param("commodityNo")String commodityNo,@Param("id")int id);

	public SpecialCommodity getSpecialCommodity(@Param("commodityNo")String commodityNo,@Param("specialId")int specialId);

}
