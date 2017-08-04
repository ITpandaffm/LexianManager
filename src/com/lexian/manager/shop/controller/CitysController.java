/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.shop.service.CitysService;
import com.lexian.manager.shop.service.StoreService;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
@Controller
@RequestMapping("city")
public class CitysController {
	@Autowired
	private CitysService cityService;
	@Autowired
	private StoreService storeService;
	//city/getCities.do?parentId=1
		@ResponseBody
		@RequestMapping("getCities.do")
		public ResultHelper getCities(Integer parentId){
			return cityService.getCities(parentId);
		}
		//city/getStoresByCitysId.do?provinceId=1&cityId=35&countyId=392
		@ResponseBody
		@RequestMapping("getStoresByCitysId.do")
		public ResultHelper getStoresByCitysId(Integer provinceId,Integer cityId,Integer countyId){
			return storeService.getStoresByCitysId(provinceId,cityId,countyId);
		}
}
