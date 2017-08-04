/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.city.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.city.dao.StoreDao;
import com.lexian.mall.city.service.StoreService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Service
public class StoreServiceImpl implements StoreService{

	
	@Autowired
	private StoreDao storeDao;
	
	@Override
	public ResultHelper getStoresByCitysId(Integer provinceId,Integer cityId,Integer countyId) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, storeDao.getStoresByCitysId(provinceId,cityId,countyId));
	}
	
}
