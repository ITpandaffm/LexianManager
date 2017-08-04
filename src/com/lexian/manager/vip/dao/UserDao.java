/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.vip.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.vip.bean.User;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface UserDao {
	
	public List<User> getUsersPage(Map<String,Object> params);
	
	public void updateUser(User user);
}
