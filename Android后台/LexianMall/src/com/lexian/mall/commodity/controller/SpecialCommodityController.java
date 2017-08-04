/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.mall.commodity.service.SpecialCommodityService;
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
@RequestMapping("specialCommodity")
public class SpecialCommodityController {

	@Autowired
	private SpecialCommodityService specialCommodityService;
	public SpecialCommodityService getSpecialCommodityService() {
		return specialCommodityService;
	}
	public void setSpecialCommodityService(SpecialCommodityService specialCommodityService) {
		this.specialCommodityService = specialCommodityService;
	}
	
	@ResponseBody
	@RequestMapping("getSpecialCommodity.do")
	public ResultHelper getSpecialCommodity(int specialId){
		
		ResultHelper result=specialCommodityService.getSpecialCommodity(specialId);
		return result;
		//specialCommodity/getSpecialCommodity.do?specialId=1
		}
	
	@ResponseBody
	@RequestMapping("getCommodityStoreByStoreNo.do")
	public ResultHelper getCommodityStoreByStoreNo(String storeNo,Integer pageNo){
		
		ResultHelper result=specialCommodityService.getCommodityStoreByStoreNo(storeNo,pageNo);
		return result;
		//specialCommodity/getCommodityStoreByStoreNo.do?storeNo=1001
		}
	@ResponseBody
	@RequestMapping("getSpecials.do")
	public ResultHelper getSpecials(Integer pageNo){
		
		return specialCommodityService.getSpecials(pageNo);
		//specialCommodity/getSpecials.do
		}
}
