/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.bean.CommoditySpec;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface CommodityDao {
	
	public List<Commodity> getCommoditiesPage(Map<String, Object> params);
	
	public List<Commodity> getCommodityByCategoryId(int categoryId);
	
	public Commodity getCommodityByCommodityNo(String commodityNo);
	
	public Commodity getCommodityById(int id);
	
	public void updateCommodity(Commodity commodity);
	
	public void addCommodity(Commodity commodity);
	
	public void addCommoditySpec(CommoditySpec commoditySpec);
	
	public void deleteCommoditySpec(String commodityNo);
	
	public void addCommodityPicture(@Param("commodityNo")String commodityNo,@Param("pictureUrl")String pictureUrl);

	public void deleteCommodityPicture(@Param("commodityNo")String commodityNo);
}
