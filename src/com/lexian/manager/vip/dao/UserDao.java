package com.lexian.manager.vip.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.vip.bean.User;


public interface UserDao {
	
	public List<User> getUsersPage(Map<String,Object> params);
	
	public void updateUser(User user);
}
