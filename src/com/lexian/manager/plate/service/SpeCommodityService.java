/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.plate.service;

import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SpeCommodityService {
	
	public ResultHelper deleteSpeCommodity(int id);
	
	public ResultHelper getSpecialCommodities(int id,Page page);
	
	public ResultHelper addSpecialCommodities(String commodityNo,int id);
}
