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

import com.lexian.mall.user.bean.Trolley;
import com.lexian.mall.user.service.TrolleyService;
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
@RequestMapping("user/trolley")
@SessionAttributes(value = { "userId" }, types = { String.class })
public class TrolleyController {

	@Autowired
	private TrolleyService trolleyService;

	// trolley/addCommodityToTrolley.do?userId=246e07f1-1577-4c45-a646-ce179f996df8&storeNo=1001&commodityNo=6946881700476&amount=2&listPrice=10.00
	@ResponseBody
	@RequestMapping("addCommodityToTrolley.do")
	public ResultHelper addCommodityToTrolley(Map<String, Object> model, Trolley trolley) {
		String userId = (String) model.get("userId");
		trolley.setUserId(userId);
		return trolleyService.addCommodityToTrolley(trolley);
	}

	// trolley/updateTrolley.do?amount=5&id=10
	@ResponseBody
	@RequestMapping("updateTrolley.do")
	public ResultHelper updateTrolley(Map<String, Object> model, Trolley trolley) {
		String userId = (String) model.get("userId");
		trolley.setUserId(userId);
		return trolleyService.updateTrolley(trolley);
	}

	// trolley/deleteTrolley.do
	@ResponseBody
	@RequestMapping("deleteTrolley.do")
	public ResultHelper deleteTrolley(Integer id) {
		return trolleyService.deleteTrolley(id);
	}

	// trolley/getTrolleys.do
	@ResponseBody
	@RequestMapping("getTrolleys.do")
	public ResultHelper getTrolleys(Map<String, Object> model) {
		String userId = (String) model.get("userId");
		return trolleyService.getTrolleys(userId);
	}

	// trolley/clearTrolley.do
	@ResponseBody
	@RequestMapping("clearTrolley.do")
	public ResultHelper clearTrolley(Map<String, Object> model) {
		String userId = (String) model.get("userId");
		return trolleyService.clearTrolley(userId);
	}
	
	
}
