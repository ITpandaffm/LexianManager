/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.authority.bean.RoleMenu;
import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.bean.CommodityPicuture;
import com.lexian.manager.goods.bean.CommoditySpec;
import com.lexian.manager.goods.dao.CommodityDao;
import com.lexian.manager.goods.service.CommodityService;
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
	public ResultHelper getCommodities(Page page) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Commodity> orderssWithStore = commodityDao.getCommoditiesPage(params);
		for (Commodity commodity : orderssWithStore) {
			commodity.setPictureUrl(UrlContant.qiNiuUrl+commodity.getPictureUrl());
		}
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getCommodityByCategoryId(int categoryId) {
		
		List<Commodity> list=commodityDao.getCommodityByCategoryId(categoryId);
			return new ResultHelper(Constant.CODE_SUCCESS,list);
		
	}

	@Override
	public ResultHelper getCommodityByCommodityNo(String commodityNo) {
		Commodity commodity=commodityDao.getCommodityByCommodityNo(commodityNo);
		if (commodity != null) {
			return new ResultHelper(Constant.CODE_SUCCESS,commodity);
		} else {
			return new ResultHelper(Constant.CODE_ENTITY_NOT_FOUND,commodity);
		}
	}

	@Override
	public ResultHelper updateCommodity(Commodity commodity) {
			Date time = new Date();
			commodity.setUpdateTime(time);
			if(commodity.getPictureUrl() !=null){
				commodity.setPictureUrl(commodity.getPictureUrl().replace("http://osnk57csd.bkt.clouddn.com", ""));
			}
			commodityDao.deleteCommodityPicture(commodity.getCommodityNo());
			commodityDao.deleteCommoditySpec(commodity.getCommodityNo());
			
			//List<CommodityPicuture> newComPic=new LinkedList<>();
			//List<CommodityPicuture> newComPic2=new LinkedList<>();
			
			List<String> listCommodityPicture = commodity.getCommodityPicuture();
			if (listCommodityPicture.size() !=0) {
				for (String string : listCommodityPicture) {
					commodityDao.addCommodityPicture(commodity.getCommodityNo(),
							string.replace("http://osnk57csd.bkt.clouddn.com", ""));
				}
			}else{
				commodityDao.addCommodityPicture(commodity.getCommodityNo(),null);
			}
				
			
			List<CommoditySpec> listCommoditySpec=commodity.getCommodtySpecs();
			if (listCommoditySpec.size() != 0) {
				for (CommoditySpec commoditySpec : listCommoditySpec) {
					commoditySpec.setCommodityNo(commodity.getCommodityNo());
					commodityDao.addCommoditySpec(commoditySpec);
				}
			}else{
				CommoditySpec commoditySpec = new CommoditySpec();
				commoditySpec.setStates(1);
				commoditySpec.setCommodityNo(commodity.getCommodityNo());
				commodityDao.addCommoditySpec(commoditySpec);
			}
			commodityDao.updateCommodity(commodity);
			return new ResultHelper(Constant.CODE_SUCCESS);
		
		
	}

	@Override
	public ResultHelper addCommodity(Commodity commodity) {
		Commodity com=commodityDao.getCommodityByCommodityNo(commodity.getCommodityNo());
		if (com != null) {
			return new ResultHelper(Constant.CODE_ENTITY_DUPLICATED);
		} else {
		
		Date time = new Date();
		commodity.setCreateTime(time);
		commodity.setStates(1);
		commodityDao.addCommodity(commodity);
		commodityDao.addCommodityPicture(commodity.getCommodityNo(),commodity.getPictureUrl());
		CommoditySpec commoditySpec = new CommoditySpec();
		commoditySpec.setStates(1);
		commoditySpec.setCommodityNo(commodity.getCommodityNo());
		commodityDao.addCommoditySpec(commoditySpec);
		return new ResultHelper(Constant.CODE_SUCCESS);
		}
	}

	@Override
	public ResultHelper getCommodityById(int id) {
		Commodity commodity = commodityDao.getCommodityById(id);
		if (commodity != null) {
			
			if(commodity.getPictureUrl()!=null){
				commodity.setPictureUrl(UrlContant.qiNiuUrl+commodity.getPictureUrl());
			}
			List<String> list = commodity.getCommodityPicuture();
			if(list.size()!=0){
				List<String> newList=new ArrayList<String>();
				for (String item : list) {
					if(item != null){
					System.out.println(item+"123");
					item=UrlContant.qiNiuUrl+item;
					newList.add(item);
					}
				}
				commodity.setCommodityPicuture(newList);
			}
			
			return new ResultHelper(Constant.CODE_SUCCESS,commodity);
		} else {
			return new ResultHelper(Constant.CODE_ENTITY_NOT_FOUND);
		}
		
	}

	@Override
	public ResultHelper updateCommodityPicture(String commodityNo, String pictureUrl) {
		commodityDao.addCommodityPicture(commodityNo, pictureUrl);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
	@Override
	public ResultHelper deleteCommodityPictrue(String commodityNo) {
		commodityDao.deleteCommodityPicture(commodityNo);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}
}
