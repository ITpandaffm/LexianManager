package com.lexian.manager.order.service;

import com.lexian.manager.order.bean.Orders;
import com.lexian.web.ResultHelper;

public interface OrdersService {

	ResultHelper getOrderss(Integer pageNo,Integer state);

	ResultHelper getOrderDetail(int id);

	ResultHelper updateOrders(Orders orders);
	
	public ResultHelper getOrderssByDate(Integer state,String start, String end, Integer pageNo);
}
