/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service;

import java.util.List;

import com.lexian.mall.user.bean.User;
import com.lexian.mall.user.helper.CashItem;
import com.lexian.mall.user.helper.OrderInfo;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface UserService {

	ResultHelper signIn(String phone, String password);

	ResultHelper cash(OrderInfo orderInfo, String password, List<CashItem> cashItems);

	ResultHelper getUserWithWallet(String userId);

	ResultHelper getUser(String userId);

	ResultHelper updatePassword(User user, String newPassword);

	ResultHelper registerUser(User user);
	
	
		
}
