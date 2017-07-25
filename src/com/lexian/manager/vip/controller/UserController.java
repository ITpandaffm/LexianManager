package com.lexian.manager.vip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.vip.bean.User;
import com.lexian.manager.vip.service.UserService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//user/getUsers.do
	@ResponseBody
	@RequestMapping("getUsers.do")
	public ResultHelper getUsers(Page page){
		
		return userService.getUsers(page);
	}
	//user/updateUser.do?id=146fe691-f6fd-4400-bbdb-d5835fb8207c&status=-1
	@ResponseBody
	@RequestMapping("updateUser.do")
	public ResultHelper updateUser(User user){
		
		return userService.updateUser(user);
	}
	
}
