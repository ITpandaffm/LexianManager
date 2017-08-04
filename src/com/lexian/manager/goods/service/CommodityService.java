/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.goods.service;

import com.lexian.manager.goods.bean.Commodity;
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
public interface CommodityService {
	
	public ResultHelper getCommodities(Page page);
	
	public ResultHelper getCommodityByCategoryId(int categoryId);
	
	public ResultHelper getCommodityByCommodityNo(String commodityNo);
	
	public ResultHelper updateCommodity(Commodity commodity);
	
	public ResultHelper addCommodity(Commodity commodity);

	public ResultHelper getCommodityById(int id);
	
	public ResultHelper deleteCommodityPictrue(String commodityNo);
	
	public ResultHelper updateCommodityPicture(String commodityNo,String pictureUrl);
}
