/**
*  Copyright 2017  Chinasofti , Inc. All rights reserved.
*/
package com.lexian.manager.statistics.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.statistics.bean.CommodityStatistics;
import com.lexian.manager.statistics.bean.StoreCommodityStatistics;
/**
 * 
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 郝伟
 * @version 1.0
 */
public interface StatisticsDao {
	public List<CommodityStatistics> getCommodityBrowseStatistics(@Param("type")Integer type,@Param("size")Integer size);

	public List<CommodityStatistics> getCommodityCollectionStatistics(@Param("type")Integer type, @Param("size")Integer size);

	public List<CommodityStatistics> getCommodityBuyStatistics(@Param("type")Integer type, @Param("size")Integer size);

	public List<StoreCommodityStatistics> getStoreCommodityBuyStatistics(@Param("storeNo")String storeNo, @Param("type")Integer type, @Param("size")Integer size);

	public List<StoreCommodityStatistics> getStoreCommodityCollectionStatistics(@Param("storeNo")String storeNo, @Param("type")Integer type,@Param("size") Integer size);

	public Integer getStoreCommodityBuyCount(@Param("storeNo")String storeNo,@Param("type")Integer type);

	public Integer getStoreCommodityCollectionCount(@Param("storeNo")String storeNo,@Param("type")Integer type);

	public Integer getCommodityBrowseCount(Integer type);

	public Integer getCommodityBuyCount(Integer type);

	public Integer getCommodityCollectionCount(Integer type);
	
	
	
}
