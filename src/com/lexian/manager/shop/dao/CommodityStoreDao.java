package com.lexian.manager.shop.dao;

import java.util.List;
import java.util.Map;

import com.lexian.manager.shop.bean.CommodityStore;

public interface CommodityStoreDao {
	//Map<String,Object> params
	public List<CommodityStore> getCommdityByStoreNoPage(Map<String,Object> params);
    public void updateCommdityStore(CommodityStore commoditystore);
    public void addCommodityStore(CommodityStore commoditystore);
}
