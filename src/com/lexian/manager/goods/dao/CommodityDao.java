package com.lexian.manager.goods.dao;

import java.util.List;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.web.ResultHelper;

public interface CommodityDao {
	public List<Commodity> getCommodities();
	
	public Commodity getCommodityByName(String name);
	
	public Commodity getCommodityBycommodityNo(String commodityNo);
	
	public Commodity getCommodityById(int id);
	
	public void updateCommodity(int id,Commodity commodity);
	
	public void addCommodity(Commodity commodity);
}
