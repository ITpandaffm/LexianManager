/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.mall.commodity.bean.CommodityStore;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface CommodityStoreDao {

	public CommodityStore getCommodityStoreByCommodityNoAndStoreNo(@Param("commodityNo") String commodityNo,
			@Param("storeNo") String storeNo);
	public void updateCommodityStoreAmontByBatch(List<CommodityStore> commodityStores);
}
