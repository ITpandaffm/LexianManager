/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.mall.user.service.OrdersService;
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
@RequestMapping("user/order")
@SessionAttributes(value = { "userId" }, types = { String.class })
public class OrderController {
	@Autowired
	private OrdersService ordersService;

	@ResponseBody
	@RequestMapping("getOrders.do")
	public ResultHelper getOrders(Map<String, Object> model, Integer pageNo, Integer state) {
		String userId = (String) model.get("userId");
		return ordersService.getOrderss(userId, pageNo, state);
	}

	// order/getOrderDetail.do?id=4
	@ResponseBody
	@RequestMapping("getOrderDetail.do")
	public ResultHelper getOrderDetail(int id) {

		return ordersService.getOrderDetail(id);
	}
}
