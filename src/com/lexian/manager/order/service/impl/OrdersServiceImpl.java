/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.order.bean.OrderItem;
import com.lexian.manager.order.bean.Orders;
import com.lexian.manager.order.dao.OrdersDao;
import com.lexian.manager.order.service.OrdersService;
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
 * @author 郝伟
 * @version 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public ResultHelper getOrderss(Page page, Integer state) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);

		if (state != null) {
			params.put("state", state);
		}

		List<Orders> orderssWithStore = ordersDao.getOrderssWithStorePage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);

		return result;
	}

	@Override
	public ResultHelper getOrderDetail(int id) {
		Orders orders=ordersDao.getOrdersWithUserAndOrderItemsStore(id);
		
		for(OrderItem item:orders.getOrderItems()){
			Commodity commodity=item.getCommodity();
			commodity.setPictureUrl(UrlContant.qiNiuUrl+"/"+commodity.getPictureUrl());
		}
		
		return new ResultHelper(Constant.CODE_SUCCESS,orders );
	}

	@Override
	public ResultHelper updateOrders(Orders orders) {

		ordersDao.updateOrders(orders);

		return new ResultHelper(Constant.CODE_SUCCESS);
	}

	@Override
	public ResultHelper getOrderssByDate(Integer state, String start, String end, Page page) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);

		if (state != null) {
			params.put("state", state);
		}

		params.put("start", start);
		params.put("end", end);
		List<Orders> orderssWithStore = ordersDao.getOrderssByDatePage(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.CODE_SUCCESS, page);
		return result;
	}

}
