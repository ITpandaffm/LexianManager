package com.lexian.manager.order.dao;

import java.util.List;

import com.lexian.manager.order.bean.OrderItem;

public interface OrderItemDao {
	
	public List<OrderItem> getOrderItemsWithCommodity(int ordersId);

}
