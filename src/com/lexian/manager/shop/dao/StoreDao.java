/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.shop.bean.Store;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public interface StoreDao {
	// Map<String,Object> params
	public List<Store> getAllStorePage(Map<String, Object> params);

	public Store getStoreByStoreNo(String storeNo);

	public void addStore(Store store);

	public void updateStore(Store store);

	public List<Store> getStoresByCitysId(@Param("pid") Integer provinceId, @Param("cityId") Integer cityId,
			@Param("countyId") Integer countyId);
}
