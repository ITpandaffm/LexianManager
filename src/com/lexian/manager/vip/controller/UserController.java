/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.vip.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.vip.bean.User;
import com.lexian.manager.vip.service.UserService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
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
	public ResultHelper updateUser(@Valid User user){
		
		return userService.updateUser(user);
	}
	
}
