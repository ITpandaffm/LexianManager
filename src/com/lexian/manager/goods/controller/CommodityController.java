/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.service.CommodityService;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Controller
@RequestMapping("commodity")
@SessionAttributes(value = { "isLogining" }, types = { Boolean.class })
public class CommodityController {

	@Autowired
	private CommodityService commodityService;

	public CommodityService getCommodityService() {
		return commodityService;
	}

	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	/**
	 * 获取商品信息commodityPicuture是商品的附图，commodtySpecs是商品的规格信息
	 * categoryView是商品的类别信息
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCommodities.do")
	public ResultHelper getCommodities(Page page) {

		ResultHelper result = commodityService.getCommodities(page);
		// commodity/getCommodities.do?pageNo=1
		return result;

	}
	/**
	 * 三级联动返回的第三级id是就是categoryId
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCommodityByCategoryId.do")
	public ResultHelper getCommodityByCategoryId(int categoryId) {
		ResultHelper result = commodityService.getCommodityByCategoryId(categoryId);

		return result;
		//commodity/getCommodityByCategoryId.do?categoryId=226
	}

	@ResponseBody
	@RequestMapping("getCommodityBycommodityNo.do")
	public ResultHelper getCommodityByCommodityNo(String commodityNo) {

		ResultHelper result = commodityService.getCommodityByCommodityNo(commodityNo);

		return result;
	}

	@ResponseBody
	@RequestMapping("getCommodityById.do")
	public ResultHelper getCommodityById(int id) {
		ResultHelper result = commodityService.getCommodityById(id);
		return result;
		// commodity/getCommodityById.do?id=46
	}
	
	/**
	 * 更新commodity
	 * commodityPicture是附图
	 * commoditySpec是规格信息
	 * categoryId是类别信息，只需要传回categoryId
	 * @param commodity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCommodity.do")
	public ResultHelper updateCommodity(@RequestBody @Valid Commodity commodity){
		ResultHelper result = commodityService.updateCommodity(commodity);
		return result;
		//commodity/updateCommodity.do
	}

	@ResponseBody
	@RequestMapping("addCommodity.do")
	public ResultHelper addCommodity(@Valid Commodity commodity) {
		ResultHelper result = commodityService.addCommodity(commodity);
		return result;
		// commodity/addCommodity.do?commodityNo=9787556806671
	}
}
