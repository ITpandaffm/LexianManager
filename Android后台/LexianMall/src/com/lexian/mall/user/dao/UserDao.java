/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.dao;

import org.apache.ibatis.annotations.Param;

import com.lexian.mall.user.bean.User;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface UserDao {
	
	
	public User getUserByPhoneAndPassword(@Param("phone") String phone,@Param("password") String password);

	public User getUserWithWalletById(String userId);

	public User getUserById(String userId);

	public Integer checkPassword(User user);

	public void updatePassword(@Param("userId")String userId, @Param("newPassword")String newPassword);

	public Integer checkIsPhoneUsed(String phone);

	public void addUser(User user);
	
}
