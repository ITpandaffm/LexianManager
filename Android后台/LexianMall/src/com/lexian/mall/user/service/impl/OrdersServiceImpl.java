/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.mall.commodity.bean.Commodity;
import com.lexian.mall.user.bean.OrderItem;
import com.lexian.mall.user.bean.Orders;
import com.lexian.mall.user.dao.OrdersDao;
import com.lexian.mall.user.service.OrdersService;
import com.lexian.utils.Constant;
import com.lexian.utils.UrlContstant;
import com.lexian.web.Page;
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
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public ResultHelper getOrderss(String userId,Integer pageNo,Integer state) {

		Page page = new Page();

		page.setPageNo(pageNo);

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);

		if (state != null) {
			params.put("state", state);
		}
		params.put("userId", userId);
		List<Orders> orderssWithStore = ordersDao.getOrderssWithStorePage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getOrderDetail(int id) {
		Orders orders=ordersDao.getOrdersWithUserAndAndOrderItemsStore(id);
		for(OrderItem oi:orders.getOrderItems()){
			Commodity c=oi.getCommodity();
			c.setPictureUrl(UrlContstant.QINIU_ADDRESS+"/"+c.getPictureUrl());
		}
		return new ResultHelper(Constant.CODE_SUCCESS, orders);
	}

	


}
