/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.mall.commodity.service;

import com.lexian.web.ResultHelper;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public interface SpecialCommodityService {

	public ResultHelper getSpecialCommodity(int specialId);
	
	public ResultHelper getCommodityStoreByStoreNo(String storeNo,Integer pageNo);
	
	public ResultHelper getSpecials(Integer pageNo);
}
