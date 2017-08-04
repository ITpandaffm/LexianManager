/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.statistics.service;

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
public interface StatisticsService {

	ResultHelper getCommodityBrowseStatistics(Integer type, Integer size);

	ResultHelper getCommodityCollectionStatistics(Integer type, Integer size);

	ResultHelper getCommodityBuyStatistics(Integer type, Integer size);

	ResultHelper getStoreCommodityBuyStatistics(String storeNo, Integer type, Integer size);

	ResultHelper getStoreCommodityCollectionStatistics(String storeNo, Integer type, Integer size);

	ResultHelper getCommodityCollectionCount(Integer type);

	ResultHelper getCommodityBuyCount(Integer type);

	ResultHelper getCommodityBrowseCount(Integer type);

	ResultHelper getStoreCommodityCollectionCount(String storeNo,Integer type);

	ResultHelper getStoreCommodityBuyCount(String storeNo,Integer type);
	
}
