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
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

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
		return new ResultHelper(Constant.code_success);
	}
	
	@Override
	public ResultHelper getSpecialCommodities(int id,Page page) {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("id", id);
		List<SpecialCommodity> orderssWithStore = speCommodityDao.getSpecialCommoditiesPage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
		
	}

	@Override
	public ResultHelper addSpecialCommodities(String commodityNo,int specialId) {
		SpecialCommodity specialCommodity = speCommodityDao.getSpecialCommodity(commodityNo,specialId);
		if(specialCommodity !=null){
			return new ResultHelper(Constant.code_entity_duplicated);
		}else{
		speCommodityDao.addSpecialCommodities(commodityNo, specialId);
		return new ResultHelper(Constant.code_success);
		}
	}

}
