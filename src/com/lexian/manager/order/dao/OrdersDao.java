package com.lexian.manager.order.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.order.bean.Orders;

public interface OrdersDao {

	public List<Orders> getOrderssWithStorePage(Map<String, Object> params);
	
	public Orders getOrdersWithUserAndAndOrderItemsStore(Integer id);
	
	public void updateOrders(Orders orders);

	public List<Orders> getOrderssByDatePage(Map<String, Object> params);

}
