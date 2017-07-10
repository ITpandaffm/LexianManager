package com.lexian.manager.vip.service;

import com.lexian.manager.vip.bean.User;
import com.lexian.web.ResultHelper;

public interface UserService {
	
	public ResultHelper getUsers();
	
	public ResultHelper updateUser(User user);
}
