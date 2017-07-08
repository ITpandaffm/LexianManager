package com.lexian.manager.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.dao.CommodityDao;
import com.lexian.manager.goods.service.CommodityService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;

@Service
public class CommodityServiceImpl implements CommodityService{
	
	@Autowired
	private CommodityDao commodityDao;

	public CommodityDao getCommodityDao() {
		return commodityDao;
	}

	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

	@Override
	public ResultHelper getCommodities() {
		List<Commodity> list = commodityDao.getCommodities();
		
		return new ResultHelper(Constant.code_success,list);
	}

	@Override
	public ResultHelper getCommodityByName(String name) {
		
		Commodity commodity=commodityDao.getCommodityByName(name);
		
		if (commodity != null) {
			return new ResultHelper(Constant.code_success,commodity);
		} else {
			return new ResultHelper(Constant.code_entity_not_found,commodity);
		}
	}

	@Override
	public ResultHelper getCommodityBycommodityNo(String commodityNo) {
		Commodity commodity=commodityDao.getCommodityBycommodityNo(commodityNo);
		if (commodity != null) {
			return new ResultHelper(Constant.code_success,commodity);
		} else {
			return new ResultHelper(Constant.code_entity_not_found,commodity);
		}
	}

	@Override
	public ResultHelper updateCommodity(int id,Commodity commodity) {
		
		commodityDao.updateCommodity(id,commodity);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper addCommodity(Commodity commodity) {
		commodityDao.addCommodity(commodity);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper getCommodityById(int id) {
		Commodity commodity = commodityDao.getCommodityById(id);
		if (commodity != null) {
			return new ResultHelper(Constant.code_success,commodity);
		} else {
			return new ResultHelper(Constant.code_entity_not_found,commodity);
		}
		
	}

}
