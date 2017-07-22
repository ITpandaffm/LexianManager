package com.lexian.manager.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.order.bean.Orders;
import com.lexian.manager.shop.bean.Store;
import com.lexian.manager.shop.dao.StoreDao;
import com.lexian.manager.shop.service.StoreService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreDao storeDao;

	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	// Integer pageNo
	// Page page = new Page();
	// if (pageNo != null) {
	// page.setPageNo(pageNo);
	// }
	// Map<String, Object> params = new HashMap<>();
	// params.put("page", page);
	// List<Store> list=storeDao.getAllStore(params);
	// page.setData(list);
	// ResultHelper result = new ResultHelper(Constant.code_success, page);
	// return result;
	@Override
	public ResultHelper getAllStore(Integer pageNo) {
		// TODO Auto-generated method stub
		/*
		 * List<Store> list=storeDao.getAllStore(); return new
		 * ResultHelper(Constant.code_success,list);
		 */
		Page page = new Page();
		page.setPageNo(pageNo);
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Store> list = storeDao.getAllStorePage(params);
		page.setData(list);
		ResultHelper result = new ResultHelper(Constant.code_success, page);
		return result;
	}

	@Override
	public ResultHelper addStore(Store store) {
		storeDao.addStore(store);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper updateStore(Store store) {
		storeDao.updateStore(store);
		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper getStoresByCitysId(Integer provinceId, Integer cityId, Integer countyId) {
		// TODO Auto-generated method stub
		return new ResultHelper(Constant.code_success, storeDao.getStoresByCitysId(provinceId, cityId, countyId));
	}

	@Override
	public ResultHelper getStoreByStoreNo(String storeNo) {
		// TODO Auto-generated method stub
		storeDao.getStoreByStoreNo(storeNo);
		Store store=storeDao.getStoreByStoreNo(storeNo);
		ResultHelper result=new ResultHelper(Constant.code_success,store);
		return result;
	}

}
