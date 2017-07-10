package com.lexian.manager.vip.dao;

import java.util.List;

import com.lexian.manager.vip.bean.User;


public interface UserDao {
	
	public List<User> getUsers();
	
	public void updateUser(User user);
}
