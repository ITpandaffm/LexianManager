/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.mall.commodity.bean.Browse;
import com.lexian.mall.commodity.service.BrowseService;
import com.lexian.mall.commodity.service.SortCommodityService;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Controller
@RequestMapping("sortCommodity")
@SessionAttributes(value = { "userId" }, types = { String.class })
public class SortCommodityController {

	@Autowired
	private SortCommodityService sortCommodityService;
	@Autowired
	private BrowseService browseService;

	public SortCommodityService getSortCommodityService() {
		return sortCommodityService;
	}

	public void setSortCommodityService(SortCommodityService sortCommodityService) {
		this.sortCommodityService = sortCommodityService;
	}

	@ResponseBody
	@RequestMapping("getFirstCategory.do")
	public ResultHelper getFirstCategory() {
		// sortCommodity/getFirstCategory.do
		ResultHelper result = sortCommodityService.getFirstCategory();
		return result;

	}

	@ResponseBody
	@RequestMapping("getSecondCategory.do")
	public ResultHelper getSecondCategory(int parentId) {
		// sortCommodity/getSecondCategory.do?parentId=46
		ResultHelper result = sortCommodityService.getSecondCategory(parentId);
		return result;
	}

	@ResponseBody
	@RequestMapping("getCommoditiesByCategoryId.do")
	public ResultHelper getCommoditiesByCategoryId(int categoryId, Integer pageNo) {
		// sortCommodity/getCommoditiesByCategoryId.do?categoryId=227
		ResultHelper result = sortCommodityService.getCommoditiesByCategoryId(categoryId, pageNo);
		return result;
	}

	@ResponseBody
	@RequestMapping("getCommodityStore.do")
	public ResultHelper getCommodityStore(String commodityNo, String storeNo,Map<String, Object> model) {
		// sortCommodity/getCommodityStore.do?commodityNo=6946881700698&storeNo=1001
		ResultHelper result = sortCommodityService.getCommodityStore(commodityNo, storeNo);
		
		String userId = (String) model.get("userId");
		
		Browse browse=new Browse();
		browse.setUserId(userId);
		browse.setCommodityNo(commodityNo);
		browseService.InsertBrowse(browse);
		
		return result;
	}
}
