/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lexian.mall.user.bean.User;
import com.lexian.mall.user.helper.CashParams;
import com.lexian.mall.user.service.UserService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Controller
@RequestMapping("user")
@SessionAttributes(value={"userId"},types={String.class})
public class UserController {
	
	@Autowired
	private UserService userService;

	
	
	//user/signIn.do?phone=13800138000&password=1
	@ResponseBody
	@RequestMapping("signIn.do")
	public ResultHelper signIn(String phone, String password, HttpSession session,Map<String, Object> model) {
		ResultHelper result = userService.signIn(phone, password);
		if (result.getCode() == Constant.CODE_SUCCESS) {
			User user = (User) result.getData();
			model.put("userId", user.getId());
		}
		return result;
	}
	
	//
	@ResponseBody
	@RequestMapping("cash.do")
	public ResultHelper cash(Map<String,Object> model,@RequestBody CashParams params) {
		
		String userId=(String) model.get("userId");
		params.getOrderInfo().setUserId(userId);
		return userService.cash(params.getOrderInfo(),params.getPassword(),params.getCashItems());
	}
	//user/getUserWithWallet.do
	@ResponseBody
	@RequestMapping("getUserWithWallet.do")
	public ResultHelper getUserWithWallet(Map<String,Object> model) {
		
		String userId=(String) model.get("userId");
		return userService.getUserWithWallet(userId);
	}
	@ResponseBody
	@RequestMapping("getUser.do")
	public ResultHelper getUser(Map<String,Object> model) {
		
		String userId=(String) model.get("userId");
		return userService.getUser(userId);
	}
	/**
	 * 退出登录
	 * @param values
	 * @return
	 */
	@ResponseBody
	@RequestMapping("signOut.do")
	public ResultHelper signOut(SessionStatus status) {
		status.setComplete();
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
	/**
	 * 修改密码
	 * @param managerId
	 * @param newPassword
	 * @return
	 */
	// manager/updateManagerPassword.do?password=1&newPass=2
	@ResponseBody
	@RequestMapping("updatePassword.do")
	public ResultHelper updatePassword(User user,String newPassword,Map<String, Object> model) {
		user.setId((String) model.get("userId"));
		return userService.updatePassword(user,newPassword);
	}
	/**
	 * 修改密码
	 * @param managerId
	 * @param newPassword
	 * @return
	 */
	// manager/updateManagerPassword.do?password=1&newPass=2
	@ResponseBody
	@RequestMapping("registerUser.do")
	public ResultHelper registerUser(User user,Map<String, Object> model,HttpSession session) {
		ResultHelper result=userService.registerUser(user);
		if (result.getCode() == Constant.CODE_SUCCESS) {
			String userId = (String) result.getData();
			model.put("userId", userId);
		}
		return result;
	}
	
}
