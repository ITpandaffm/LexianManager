/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.mall.user.service.WalletRecordService;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Controller
@RequestMapping("user/wallet")
public class WalletController {
	
	@Autowired
	private WalletRecordService walletRecordService;
	//  wallet/getWalletRecords.do?walletId=129
	@RequestMapping("getWalletRecords.do")
	@ResponseBody
	public ResultHelper getWalletRecords(Integer walletId){
		return walletRecordService.getWalletRecords(walletId);
	}
}
