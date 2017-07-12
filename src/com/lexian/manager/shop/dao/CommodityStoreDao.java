package com.lexian.manager.shop.dao;

import java.util.List;

import com.lexian.manager.shop.bean.CommodityStore;

public interface CommodityStoreDao {
    public List<CommodityStore> getCommdityByStoreNoPage(String storeNo);
}
