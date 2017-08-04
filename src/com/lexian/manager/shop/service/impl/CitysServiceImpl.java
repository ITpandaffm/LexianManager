/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.shop.dao.CitysDao;
import com.lexian.manager.shop.service.CitysService;
import com.lexian.utils.Constant;
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
@Service
public class CitysServiceImpl implements CitysService {

	@Autowired
	private CitysDao citysDao;
	
	@Override
	public ResultHelper getCities(Integer parentId) {
		// TODO Auto-generated method stub
		ResultHelper result=new ResultHelper(Constant.CODE_SUCCESS,citysDao.getCitiesByParentId(parentId));	
		return result;
	}

}
