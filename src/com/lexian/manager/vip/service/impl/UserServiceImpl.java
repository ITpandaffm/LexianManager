package com.lexian.manager.vip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.vip.bean.User;
import com.lexian.manager.vip.dao.UserDao;
import com.lexian.manager.vip.service.UserService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public ResultHelper getUsers(Integer pageNo) {

		Page page = new Page();

		page.setPageNo(pageNo);

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<User> privileges = userDao.getUsersPage(params);
		page.setData(privileges);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;

	}

	@Override
	public ResultHelper updateUser(User user) {
		userDao.updateUser(user);
		return new ResultHelper(Constant.code_success);
	}

}
