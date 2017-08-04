/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.order.service;

import com.lexian.manager.order.bean.Orders;
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
public interface OrdersService {

	ResultHelper getOrderss(Page page,Integer state);

	ResultHelper getOrderDetail(int id);

	ResultHelper updateOrders(Orders orders);
	
	public ResultHelper getOrderssByDate(Integer state,String start, String end,Page page);
}
