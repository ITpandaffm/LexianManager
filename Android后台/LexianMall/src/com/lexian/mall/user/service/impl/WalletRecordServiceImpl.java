/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.user.dao.WalletRecordDao;
import com.lexian.mall.user.service.WalletRecordService;
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
public class WalletRecordServiceImpl implements WalletRecordService{

	@Autowired
	private WalletRecordDao walletRecordDao;
	
	
	@Override
	public ResultHelper getWalletRecords(Integer walletId) {
		
		return new ResultHelper(Constant.CODE_SUCCESS,walletRecordDao.getWalletRecords(walletId));
	}

}
