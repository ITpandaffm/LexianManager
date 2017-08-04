/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Category;
import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.commodity.bean.CommodityPicture;
import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.commodity.dao.SortCommodityDao;
import com.lexian.mall.commodity.service.SortCommodityService;
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
public class SortCommodityServiceImpl implements SortCommodityService{
	
	@Autowired
	private SortCommodityDao sortCommodityDao;

	public SortCommodityDao getSortCommodityDao() {
		return sortCommodityDao;
	}

	public void setSortCommodityDao(SortCommodityDao sortCommodityDao) {
		this.sortCommodityDao = sortCommodityDao;
	}

	@Override
	public ResultHelper getFirstCategory() {
		List<Category> list=sortCommodityDao.getFirstCategory();
		return new ResultHelper(Constant.CODE_SUCCESS,list);
	}

	@Override
	public ResultHelper getSecondCategory(int parentId) {
		
		List<Category> listSecond=sortCommodityDao.getSecondCategory(parentId);
		for (Category category : listSecond) {
			List<Category> listThird =sortCommodityDao.getThirdCategory(category.getId());
			category.setThirdCategory(listThird);
		}
		return new ResultHelper(Constant.CODE_SUCCESS,listSecond);
	}

	@Override
	public ResultHelper getCommoditiesByCategoryId(int categoryId,Integer pageNo) {
		Page page= new Page();
		page.setPageNo(pageNo);
		
		//page.setTotalSize(sortCommodityDao.getCountCommodity());
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("categoryId", categoryId);
		List<CommodityStore> orderssWithStore = sortCommodityDao.getCommoditiesByCategoryIdPage(params);
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
	public ResultHelper getCommodityStore(String commodityNo, String storeNo) {
		
		CommodityStore commodityStore = sortCommodityDao.getCommodityStore(commodityNo, storeNo);
		Commodity commodity=sortCommodityDao.getCommodityByCommodityNo(commodityNo);
		commodity.setPictureUrl(UrlContstant.QINIU_ADDRESS+commodity.getPictureUrl());
		commodityStore.setCommodity(commodity);
		commodityStore.setStore(sortCommodityDao.getSotreByStoreNo(storeNo));
		List<CommodityPicture> list=sortCommodityDao.getCommodityPicture(commodityNo);
		for (CommodityPicture commodityPicture : list) {
			commodityPicture.setPictureUrl(UrlContstant.QINIU_ADDRESS+commodityPicture.getPictureUrl());
		}
		commodityStore.setCommodityPictures(list);
		//commodityStore.setCommodityPicutures(sortCommodityDao.getCommodityPicture(commodityNo));
		return new ResultHelper(Constant.CODE_SUCCESS,commodityStore);
	}

}
