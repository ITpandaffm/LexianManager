/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.dao;

import org.apache.ibatis.annotations.Param;

import com.lexian.mall.user.bean.Wallet;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface WalletDao {

	public Wallet getWalletByUserIdAndPayPassword(@Param("userId") String userId,
			@Param("payPassword") String payPassword);

	public void updateBalance(Wallet wallet);

}
