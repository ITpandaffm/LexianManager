/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.plate.bean.SpecialCommodity;
import com.lexian.manager.plate.dao.SpeCommodityDao;
import com.lexian.manager.plate.service.SpeCommodityService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContant;
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
@Service
public class SpeCommodityServiceImpl implements SpeCommodityService{
	
	@Autowired
	private SpeCommodityDao speCommodityDao;

	
	
	public SpeCommodityDao getSpeCommodityDao() {
		return speCommodityDao;
	}

	public void setSpeCommodityDao(SpeCommodityDao speCommodityDao) {
		this.speCommodityDao = speCommodityDao;
	}

	@Override
	public ResultHelper deleteSpeCommodity(int id) {
		speCommodityDao.deleteSpeCommodity(id);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
	
	@Override
	public ResultHelper getSpecialCommodities(int id,Page page) {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("id", id);
		List<SpecialCommodity> orderssWithStore = speCommodityDao.getSpecialCommoditiesPage(params);
		for (SpecialCommodity specialCommodity : orderssWithStore) {
			specialCommodity.setPictureUrl(UrlContant.qiNiuUrl+specialCommodity.getPictureUrl());
		}
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
		
	}

	@Override
	public ResultHelper addSpecialCommodities(String commodityNo,int specialId) {
		SpecialCommodity specialCommodity = speCommodityDao.getSpecialCommodity(commodityNo,specialId);
		if(specialCommodity !=null){
			return new ResultHelper(Constant.CODE_ENTITY_DUPLICATED);
		}else{
		speCommodityDao.addSpecialCommodities(commodityNo, specialId);
		return new ResultHelper(Constant.CODE_SUCCESS);
		}
	}

}
