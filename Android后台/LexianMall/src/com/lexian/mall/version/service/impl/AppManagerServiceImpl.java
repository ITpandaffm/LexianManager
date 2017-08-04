/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.version.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.version.bean.AppManager;
import com.lexian.mall.version.dao.AppManagerDao;
import com.lexian.mall.version.service.AppManagerService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
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
public class AppManagerServiceImpl implements AppManagerService{
	
	@Autowired
	private AppManagerDao appManagerDao;
	@Override
	public ResultHelper getLastVersion() {
		AppManager version=appManagerDao.getLastVersion();
		version.setAppUrl(UrlContstant.QINIU_ADDRESS+version.getAppUrl());
		return new ResultHelper(Constant.CODE_SUCCESS, version);
	}

}
