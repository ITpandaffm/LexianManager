/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.dao;

import java.util.List;

import com.lexian.mall.user.bean.WalletRecord;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface WalletRecordDao {
	public void insertWalletRecord(WalletRecord record);
	public List<WalletRecord> getWalletRecords(Integer walletId);
}
