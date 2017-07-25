package com.lexian.manager.shop.service;

import java.util.List;

import com.lexian.manager.shop.bean.CommodityStore;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;
public interface CommodityStoreService {
  
   public ResultHelper getCommodityByStoreNo(String storeNo,Page page);
   public ResultHelper updateCommodityStore(CommodityStore commoditystore);
   public ResultHelper addCommodityStore(List<CommodityStore>  list);
   public ResultHelper getCommodityByCategoryId(int categoryId,String storeNo);
   public ResultHelper registCommodityStore(String storeNo,String[] commodityNo,Integer type);
}
 