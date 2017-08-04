/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.commodity.bean.Special;
import com.lexian.mall.commodity.dao.SpecialCommodityDao;
import com.lexian.mall.commodity.service.SpecialCommodityService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
@Service
public class SpecialCommodityServiceImpl implements SpecialCommodityService{
	
	@Autowired
	private SpecialCommodityDao specialCommodityDao;
	

	public SpecialCommodityDao getSpecialCommodityDao() {
		return specialCommodityDao;
	}


	public void setSpecialCommodityDao(SpecialCommodityDao specialCommodityDao) {
		this.specialCommodityDao = specialCommodityDao;
	}


	@Override
	public ResultHelper getSpecialCommodity(int specialId) {

		List<Commodity> list = specialCommodityDao.getSpecialCommodity(specialId);
		for (Commodity commodity : list) {
			commodity.setPictureUrl(UrlContstant.QINIU_ADDRESS+commodity.getPictureUrl());
		}
		return new ResultHelper(Constant.CODE_SUCCESS,list);
	}


	@Override
	public ResultHelper getCommodityStoreByStoreNo(String storeNo,Integer pageNo) {
		Page page= new Page();
		page.setPageNo(pageNo);
		
		//page.setTotalSize(sortCommodityDao.getCountCommodity());
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("storeNo", storeNo);
		List<CommodityStore> orderssWithStore = specialCommodityDao.getCommoditiesByStoreNoPage(params);
		for (CommodityStore commodityStore : orderssWithStore) {
			Commodity commodity = commodityStore.getCommodity();
			commodity.setPictureUrl(UrlContstant.QINIU_ADDRESS+commodityStore.getCommodity().getPictureUrl());
			commodity.setCommodityNo(commodityStore.getCommodity().getCommodityNo());
			commodity.setName(commodityStore.getCommodity().getName());
			commodityStore.setCommodity(commodity);
		}
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);
		return result;
	}


	@Override
	public ResultHelper getSpecials(Integer pageNo) {
		
		Page page= new Page();
		page.setPageNo(pageNo);
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		
		List<Special> list = specialCommodityDao.getSpecialsPage(params);
		page.setData(list);
		return new ResultHelper(Constant.CODE_SUCCESS,page);
	}

}
