/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.user.dao;

import java.util.List;
import java.util.Map;

import com.lexian.mall.user.bean.Orders;

/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface OrdersDao {

	public List<Orders> getOrderssWithStorePage(Map<String, Object> params);
	
	public Orders getOrdersWithUserAndAndOrderItemsStore(Integer id);

	public void insertOrders(Orders orders);

}
