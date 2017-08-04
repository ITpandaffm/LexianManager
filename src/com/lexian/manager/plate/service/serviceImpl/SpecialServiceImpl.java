/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.plate.bean.Special;
import com.lexian.manager.plate.dao.SpeCommodityDao;
import com.lexian.manager.plate.dao.SpecialDao;
import com.lexian.manager.plate.service.SpecialService;
import com.lexian.utils.Constant;
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
public class SpecialServiceImpl implements SpecialService{

	@Autowired
	private SpecialDao specialDao;
	
	@Autowired
	private SpeCommodityDao speCommodityDao;
	
	public SpecialDao getSpecialDao() {
		return specialDao;
	}

	public void setSpecialDao(SpecialDao specialDao) {
		this.specialDao = specialDao;
	}

	@Override
	public ResultHelper getSpecial(Page page) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Special> orderssWithStore = specialDao.getSpecialPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper updateSpecial(int id, String name) {
		specialDao.updateSpecial(id, name);
		 return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper deleteSpecial(int id) {
		if (speCommodityDao.getCountSpeCommodities(id) !=0) {
			 return new ResultHelper(Constant.CODE_ENTITY_DUPLICATED);
		}else{
		specialDao.deleteSpecial(id);
		 return new ResultHelper(Constant.CODE_SUCCESS);
		}
	}

	@Override
	public ResultHelper addSpecial(String name) {
		Special special = specialDao.getSpecialByName(name);
		if (special != null) {
			return new ResultHelper(Constant.CODE_ENTITY_DUPLICATED);
		}else{
		specialDao.addSpecial(name);
		return new ResultHelper(Constant.CODE_SUCCESS);
		}
	}

}
