/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/package com.lexian.manager.shop.service;

import com.lexian.manager.shop.bean.Store;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public interface StoreService {
	//Integer pageNo
     public  ResultHelper getAllStore(Page page);
     public ResultHelper getStoreByStoreNo(String storeNo);
     public ResultHelper addStore(Store store);
     public ResultHelper updateStore(Store store);
     public ResultHelper getStoresByCitysId(Integer provinceId,Integer cityId,Integer countyId);
}
