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
public interface SortCommodityService {

	public ResultHelper getFirstCategory();
	
	public ResultHelper getSecondCategory(int parentId);
	
	public ResultHelper getCommoditiesByCategoryId(int categoryId,Integer pageNo);
	
	public ResultHelper getCommodityStore(String commodityNo,String storeNo);
	
}
