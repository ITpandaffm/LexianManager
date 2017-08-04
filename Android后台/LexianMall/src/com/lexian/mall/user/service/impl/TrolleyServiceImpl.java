/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.commodity.bean.CommodityStore;
import com.lexian.mall.user.bean.Trolley;
import com.lexian.mall.user.dao.CommodityStoreDao;
import com.lexian.mall.user.dao.TrolleyDao;
import com.lexian.mall.user.service.TrolleyService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
@Service
public class TrolleyServiceImpl implements TrolleyService {

	@Autowired
	private TrolleyDao trolleyDao;

	@Autowired
	private CommodityStoreDao commodityStoreDao;

	@Override
	public ResultHelper addCommodityToTrolley(Trolley trolley) {
		Integer count = trolleyDao.hasCommodityInTrolley(trolley);
		boolean isExist = count > 0 ? true : false;
		if (!isExist) {
			CommodityStore cs = commodityStoreDao.getCommodityStoreByCommodityNoAndStoreNo(trolley.getCommodityNo(),
					trolley.getStoreNo());
			trolley.setListPrice(cs.getRealPrice());
			Date date = new Date();
			trolley.setCreateTime(date);
			trolley.setUpdateTime(date);
			trolley.setTotalPrice(trolley.getAmont() * trolley.getListPrice());
			trolleyDao.addCommodityToTrolley(trolley);
		} else {
			updateTrolley(trolley);
		}

		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper updateTrolley(Trolley trolley) {

		double listPrice = trolleyDao.getListPrice(trolley);
		trolley.setListPrice(listPrice);
		trolley.setTotalPrice(trolley.getAmont() * trolley.getListPrice());
		trolley.setUpdateTime(new Date());
		trolleyDao.updateTrolley(trolley);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper deleteTrolley(Integer id) {

		trolleyDao.deleteTrolleyById(id);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper getTrolleys(String userId) {

		List<Trolley> trolleys = trolleyDao.getTrolleys(userId);

		for (Trolley t : trolleys) {
			Commodity c = t.getCommodity();
			c.setPictureUrl(UrlContstant.QINIU_ADDRESS + "/" + c.getPictureUrl());
		}

		return new ResultHelper(Constant.CODE_SUCCESS, trolleys);
	}

	@Override
	public ResultHelper clearTrolley(String userId) {
		trolleyDao.clearTrolley(userId);
		return new ResultHelper(Constant.CODE_SUCCESS);
	}

}
