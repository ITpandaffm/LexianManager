package com.lexian.manager.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.order.bean.Orders;
import com.lexian.manager.order.dao.OrderItemDao;
import com.lexian.manager.order.dao.OrdersDao;
import com.lexian.manager.order.service.OrdersService;
import com.lexian.utils.Constant;
import com.lexian.web.ResultHelper;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public ResultHelper getOrderss() {

		ResultHelper result = new ResultHelper(Constant.code_success, ordersDao.getOrderssWithStore());

		return result;
	}

	@Override
	public ResultHelper getOrderDetail(int id) {

		Object[] objects = new Object[2];

		objects[0] = ordersDao.getOrdersWithUserAndStore(id);

		objects[1] = orderItemDao.getOrderItemsWithCommodity(id);

		return new ResultHelper(Constant.code_success, objects);
	}

	@Override
	public ResultHelper updateOrders(Orders orders) {

		ordersDao.updateOrders(orders);

		return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper getOrderssByState(int state) {
		
		ResultHelper result = new ResultHelper(Constant.code_success, ordersDao.getOrderssWithStoreByState(state));
		return result;
	}

}
