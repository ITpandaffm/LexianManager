/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.statistics.service.StatisticsService;
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
@RequestMapping("statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	// statistics/getCommodityBrowseStatistics.do?type=1
	@RequestMapping("getCommodityBrowseStatistics.do")
	@ResponseBody
	public ResultHelper getCommodityBrowseStatistics(Integer type, Integer size) {

		return statisticsService.getCommodityBrowseStatistics(type, size);
	}

	// statistics/getCommodityCollectionStatistics.do?type=1
	@RequestMapping("getCommodityCollectionStatistics.do")
	@ResponseBody
	public ResultHelper getCommodityCollectionStatistics(Integer type, Integer size) {

		return statisticsService.getCommodityCollectionStatistics(type, size);
	}

	// statistics/getCommodityBuyStatistics.do?type=1
	@RequestMapping("getCommodityBuyStatistics.do")
	@ResponseBody
	public ResultHelper getCommodityBuyStatistics(Integer type, Integer size) {

		return statisticsService.getCommodityBuyStatistics(type, size);
	}

	//statistics/getStoreCommodityBuyStatistics.do?type=1&storeNo=1001
	@RequestMapping("getStoreCommodityBuyStatistics.do")
	@ResponseBody
	public ResultHelper getStoreCommodityBuyStatistics(String storeNo, Integer type, Integer size) {

		return statisticsService.getStoreCommodityBuyStatistics(storeNo, type, size);
	}
	
	@RequestMapping("getStoreCommodityCollectionStatistics.do")
	@ResponseBody
	public ResultHelper getStoreCommodityCollectionStatistics(String storeNo, Integer type, Integer size) {

		return statisticsService.getStoreCommodityCollectionStatistics(storeNo, type, size);
	}
	
	//statistics/getCommodityCollectionCount.do
	@RequestMapping("getCommodityCollectionCount.do")
	@ResponseBody
	public ResultHelper getCommodityCollectionCount(Integer type) {

		return statisticsService.getCommodityCollectionCount(type);
	}
	//statistics/getCommodityBuyCount.do
	@RequestMapping("getCommodityBuyCount.do")
	@ResponseBody
	public ResultHelper getCommodityBuyCount(Integer type) {

		return statisticsService.getCommodityBuyCount(type);
	}
	//statistics/getCommodityBrowseCount.do
	@RequestMapping("getCommodityBrowseCount.do")
	@ResponseBody
	public ResultHelper getCommodityBrowseCount(Integer type) {

		return statisticsService.getCommodityBrowseCount(type);
	}
	
	
	//statistics/getStoreCommodityCollectionCount.do
	@RequestMapping("getStoreCommodityCollectionCount.do")
	@ResponseBody
	public ResultHelper getStoreCommodityCollectionCount(String storeNo,Integer type) {

		return statisticsService.getStoreCommodityCollectionCount(storeNo,type);
	}
	//statistics/getStoreCommodityBuyCount.do
	@RequestMapping("getStoreCommodityBuyCount.do")
	@ResponseBody
	public ResultHelper getStoreCommodityBuyCount(String storeNo,Integer type) {

		return statisticsService.getStoreCommodityBuyCount(storeNo,type);
	}
	

}
