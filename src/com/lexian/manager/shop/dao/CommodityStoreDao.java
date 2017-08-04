/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.shop.bean.CommodityStore;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public interface CommodityStoreDao {
	//Map<String,Object> params
	public List<CommodityStore> getCommdityByStoreNoPage(Map<String,Object> params);
    public void updateCommdityStore(CommodityStore commoditystore);
    public void addCommodityStore(CommodityStore commoditystore);
    public List<Commodity> getCommodityByCategoryId(@Param("categoryId")int categoryId,@Param("storeNo")String storeNo);
    public void registCommodityStore(@Param("storeNo")String storeNo,@Param("commodityNo")String commodityNo,@Param("type")Integer type);
}
