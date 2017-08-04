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

import com.lexian.mall.user.bean.CommodityCollection;
import com.lexian.mall.user.service.CommodityCollectionService;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@RequestMapping("user/collection")
@Controller
@SessionAttributes(value = { "userId" }, types = { String.class })
public class CommodityCollectionController {

	@Autowired
	private CommodityCollectionService commodityCollectionService;

	// user/collection/getCommodityCollection.do
	@ResponseBody
	@RequestMapping("getCommodityCollection.do")
	public ResultHelper getCommodityCollection(Integer pageNo, Map<String, Object> model) {
		String userId = (String) model.get("userId");
		return commodityCollectionService.getCommodityCollection(pageNo, userId);
	}

	@ResponseBody
	@RequestMapping("addCommodityCollection.do")
	public ResultHelper addCommodityCollection(CommodityCollection cc, Map<String, Object> model) {
		String userId = (String) model.get("userId");
		cc.setUserId(userId);
		return commodityCollectionService.addCommodityCollection(cc);
	}

	// user/collection/deleteCommodityCollection.do?commodityNo=9787539185866&storeNo=1001
	@ResponseBody
	@RequestMapping("deleteCommodityCollection.do")
	public ResultHelper deleteCommodityCollection(CommodityCollection cc, Map<String, Object> model) {
		String userId = (String) model.get("userId");
		cc.setUserId(userId);
		return commodityCollectionService.deleteCommodityCollection(cc);
	}

	// collection/hasCommodityInCommodityCollection.do?storeNo=1004&commodityNo=6946881700476
	@ResponseBody
	@RequestMapping("hasCommodityInCommodityCollection.do")
	public ResultHelper hasCommodityInCommodityCollection(Map<String, Object> model, CommodityCollection cc) {
		String userId = (String) model.get("userId");
		cc.setUserId(userId);
		return commodityCollectionService.hasCommodityInCommodityCollection(cc);
	}

}
