package com.lexian.manager.shop.service;

import com.lexian.manager.shop.bean.CommodityStore;
import com.lexian.web.ResultHelper;

public interface CommodityStoreService {
   public ResultHelper getCommodityByStoreNo(String storeNo);
   public ResultHelper updateCommodityStore(CommodityStore commoditystore);
}
