package com.lexian.manager.goods.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.bean.CommoditySpec;
import com.lexian.manager.goods.dao.CommodityDao;
import com.lexian.manager.goods.service.CommodityService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
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
	public ResultHelper getCommodities(Integer pageNo) {
		Page page = new Page();
		page.setPageNo(pageNo);
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Commodity> orderssWithStore = commodityDao.getCommoditiesPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper getCommodityByCategoryId(int categoryId) {
		
		List<Commodity> list=commodityDao.getCommodityByCategoryId(categoryId);
			return new ResultHelper(Constant.code_success,list);
		
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
	public ResultHelper updateCommodity(Commodity commodity) {
			Date time = new Date();
			commodity.setUpdateTime(time);
			System.out.println(commodity.getUpdateTime());
			commodityDao.deleteCommodityPicture(commodity.getCommodityNo());
			commodityDao.deleteCommoditySpec(commodity.getCommodityNo());
			List<String> listCommodityPicture = commodity.getCommodityPicuture();
			if(listCommodityPicture!=null){
				for (String string : listCommodityPicture) {
					System.out.println(string);
					commodityDao.addCommodityPicture(commodity.getCommodityNo(),string);
				}
			}
			
			List<CommoditySpec> listCommoditySpec=commodity.getCommodtySpecs();
			for (CommoditySpec commoditySpec : listCommoditySpec) {
				System.out.println(commoditySpec.getCommodityNo()+commoditySpec.getSpecGroup()+commoditySpec.getSpecName());
				commodityDao.addCommoditySpec(commoditySpec);
			}
			commodityDao.updateCommodity(commodity);
			return new ResultHelper(Constant.code_success);
		
		
	}

	@Override
	public ResultHelper addCommodity(Commodity commodity) {
		Commodity com=commodityDao.getCommodityBycommodityNo(commodity.getCommodityNo());
		if (com != null) {
			return new ResultHelper(Constant.code_entity_duplicated);
		} else {
		
		Date time = new Date();
		commodity.setCreateTime(time);
		commodity.setStates(1);
		commodityDao.addCommodity(commodity);
		commodityDao.addCommodityPicture(commodity.getCommodityNo(),commodity.getPictureUrl());
		System.out.println(commodity.getCommodityNo()+commodity.getPictureUrl());
		System.out.println(commodity.getCreateTime());
		return new ResultHelper(Constant.code_success);
		}
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

	@Override
	public ResultHelper updateCommodityPicture(String commodityNo, String pictureUrl) {
		commodityDao.addCommodityPicture(commodityNo, pictureUrl);
		return new ResultHelper(Constant.code_success);
	}
	@Override
	public ResultHelper deleteCommodityPictrue(String commodityNo) {
		commodityDao.deleteCommodityPicture(commodityNo);
		return new ResultHelper(Constant.code_success);
	}
}
