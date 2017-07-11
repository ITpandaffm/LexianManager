package com.lexian.manager.order.dao;

import java.util.List;


import com.lexian.manager.order.bean.Orders;
import com.lexian.web.ResultHelper;

public interface OrdersDao {

	public List<Orders> getOrderssWithStore();
	
	public Orders getOrdersWithUserAndStore(Integer id);
	
	public void updateOrders(Orders orders);

	public List<Orders> getOrderssWithStoreByState(Integer state);

	

}
