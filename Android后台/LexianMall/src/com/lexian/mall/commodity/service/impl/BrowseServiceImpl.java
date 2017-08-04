/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Browse;
import com.lexian.mall.commodity.dao.BrowseDao;
import com.lexian.mall.commodity.service.BrowseService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Service
public class BrowseServiceImpl implements BrowseService {
	
	@Autowired
	private BrowseDao browseDao;

	@Override
	public ResultHelper InsertBrowse(Browse browse) {
		Date date = new Date();
		browse.setBrowseTime(date);
		browseDao.InsertBrowse(browse);	
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

}
