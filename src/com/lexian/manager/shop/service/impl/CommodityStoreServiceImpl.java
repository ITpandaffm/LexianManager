/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.shop.bean.CommodityStore;
import com.lexian.manager.shop.dao.CommodityStoreDao;
import com.lexian.manager.shop.service.CommodityStoreService;
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
 * @author 王子龙
 * @version 1.0
 */
@Service
public class CommodityStoreServiceImpl implements CommodityStoreService {
    @Autowired
	 private CommodityStoreDao commodityStoreDao;
	
	
    public CommodityStoreDao getCommodityStoreDao() {
		return commodityStoreDao;
	}

	public void setCommodityStoreDao(CommodityStoreDao commodityStoreDao) {
		this.commodityStoreDao = commodityStoreDao;
	}
	@Override
	public ResultHelper getCommodityByStoreNo(String storeNo,Page page) {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("storeNo",storeNo);
		List<CommodityStore> list = commodityStoreDao.getCommdityByStoreNoPage(params);
		page.setData(list);
		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);
		return result;
		/*List<CommodityStore> list=commoditystoreDao.getCommdityByStoreNo(storeNo);
		return new ResultHelper(Constant.code_success,list);*/
	}

	@Override
	public ResultHelper updateCommodityStore(CommodityStore commodityStore) {
		// TODO Auto-generated method stub
		commodityStoreDao.updateCommdityStore(commodityStore);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper addCommodityStore(List<CommodityStore> list) {
		/*System.out.println(commoditystore.getCommodityNo()+"*********"+commoditystore.getStoreNo());*/
		for (CommodityStore commodityStore : list) {
			commodityStoreDao.addCommodityStore(commodityStore);
		}
		   return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper getCommodityByCategoryId(int categoryId,String storeNo) {
		// TODO Auto-generated method stub
		List<Commodity> list=commodityStoreDao.getCommodityByCategoryId(categoryId,storeNo);
		for (Commodity commodity : list) {
			commodity.setPictureUrl(UrlContant.qiNiuUrl+commodity.getPictureUrl());
		}
		return new ResultHelper(Constant.CODE_SUCCESS,list);
	}

	@Override
	public ResultHelper registCommodityStore(String storeNo, String[] commodityNo,Integer type) {
		// TODO Auto-generated method stub
		for (String string : commodityNo) {
			commodityStoreDao.registCommodityStore(storeNo, string,type);
		}
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
    
}
