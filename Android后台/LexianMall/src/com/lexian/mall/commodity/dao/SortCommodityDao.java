/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.mall.commodity.bean.Category;
import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.commodity.bean.CommodityPicture;
import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.commodity.bean.Store;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SortCommodityDao {

	public List<Category> getFirstCategory();
	
	
	public List<Category> getSecondCategory(int parentId);
	
	public List<Category> getThirdCategory(int parenId);
	
	public List<CommodityStore> getCommoditiesByCategoryIdPage(Map<String, Object> params);
	
	public List<CommodityStore> getCommodityStoreByCommodityNo(String commodityNo);
	
	public Store getSotreByStoreNo(String storeNo);
	
	public Commodity getCommodityByCommodityNo(String commodityNo);
	
	public List<CommodityPicture> getCommodityPicture(String commodityNo);
	
	public CommodityStore getCommodityStore(@Param("commodityNo")String commodityNo,@Param("storeNo")String storeNo);
}
