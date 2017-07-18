package com.lexian.manager.shop.service;

import org.springframework.stereotype.Service;

import com.lexian.manager.shop.bean.Store;
import com.lexian.web.ResultHelper;
public interface StoreService {
	//Integer pageNo
     public  ResultHelper getAllStore(Integer pageNo);
     public ResultHelper addStore(Store store);
     public ResultHelper updateStore(Store store);
     public ResultHelper getStoresByCitysId(Integer provinceId,Integer cityId,Integer countyId);
}
