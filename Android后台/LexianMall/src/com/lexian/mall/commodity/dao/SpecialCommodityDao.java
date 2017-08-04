/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.dao;

import java.util.List;
import java.util.Map;

import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.commodity.bean.Special;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SpecialCommodityDao {

	public List<Commodity> getSpecialCommodity(int specialId);
	
	public List<CommodityStore> getCommoditiesByStoreNoPage(Map<String, Object> params);
	
	public List<Special> getSpecialsPage(Map<String, Object> params);
}
