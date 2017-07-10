package com.lexian.manager.vip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.vip.bean.User;
import com.lexian.manager.vip.dao.UserDao;
import com.lexian.manager.vip.service.UserService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResultHelper getUsers() {
		
		return new ResultHelper(Constant.code_success, userDao.getUsers());
	}

	@Override
	public ResultHelper updateUser(User user) {
		userDao.updateUser(user);
		return new ResultHelper(Constant.code_success);
	}

}
