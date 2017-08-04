/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.statistics.dao.StatisticsDao;
import com.lexian.manager.statistics.service.StatisticsService;
import com.lexian.utils.Constant;
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
@Service
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	private StatisticsDao statisticsDao;

	@Override
	public ResultHelper getCommodityBrowseStatistics(Integer type, Integer size) {
		
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityBrowseStatistics(type,size));
	}

	@Override
	public ResultHelper getCommodityCollectionStatistics(Integer type, Integer size) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityCollectionStatistics(type,size));
	}

	@Override
	public ResultHelper getCommodityBuyStatistics(Integer type, Integer size) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityBuyStatistics(type,size));
	}

	@Override
	public ResultHelper getStoreCommodityBuyStatistics(String storeNo, Integer type, Integer size) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getStoreCommodityBuyStatistics(storeNo,type,size));
	}

	@Override
	public ResultHelper getStoreCommodityCollectionStatistics(String storeNo, Integer type, Integer size) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getStoreCommodityCollectionStatistics(storeNo,type,size));
	}

	@Override
	public ResultHelper getCommodityCollectionCount(Integer type) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityCollectionCount(type));
	}

	@Override
	public ResultHelper getCommodityBuyCount(Integer type) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityBuyCount(type));
	}

	@Override
	public ResultHelper getCommodityBrowseCount(Integer type) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getCommodityBrowseCount(type));
	}

	@Override
	public ResultHelper getStoreCommodityCollectionCount(String storeNo,Integer type) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getStoreCommodityCollectionCount(storeNo,type));
	}

	@Override
	public ResultHelper getStoreCommodityBuyCount(String storeNo,Integer type) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.CODE_SUCCESS, statisticsDao.getStoreCommodityBuyCount(storeNo,type));
	}
	
	

}
