package com.lexian.manager.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.order.bean.Orders;
import com.lexian.manager.order.dao.OrderItemDao;
import com.lexian.manager.order.dao.OrdersDao;
import com.lexian.manager.order.service.OrdersService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public ResultHelper getOrderss(Integer pageNo,Integer state) {

		Page page = new Page();

		if (pageNo != null) {
			page.setPageNo(pageNo);
		}
		

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		
		if(state!=null){
			params.put("state", state);
		}
		
		List<Orders> orderssWithStore = ordersDao.getOrderssWithStorePage(params);
		page.setData(orderssWithStore);
	

		ResultHelper result = new ResultHelper(Constant.code_success, page);

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
	public ResultHelper getOrderssByDate(Integer state,String start, String end, Integer pageNo) {
		Page page = new Page();

		if (pageNo != null) {
			page.setPageNo(pageNo);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		
		if(state!=null){
			params.put("state", state);
		}
		
		params.put("start", start);
		params.put("end", end);
		List<Orders> orderssWithStore = ordersDao.getOrderssByDatePage(params);
		page.setData(orderssWithStore);
	
		ResultHelper result = new ResultHelper(Constant.code_success, page);
		return result;
	}

}
