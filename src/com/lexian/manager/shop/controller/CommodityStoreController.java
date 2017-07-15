package com.lexian.manager.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.shop.bean.CommodityStore;
import com.lexian.manager.shop.service.CommodityStoreService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("commoditystore")
public class CommodityStoreController {
	@Autowired
	private CommodityStoreService commoditystoreService;

	public CommodityStoreService getCommoditystoreService() {
		return commoditystoreService;
	}

	public void setCommoditystoreService(CommodityStoreService commoditystoreService) {
		this.commoditystoreService = commoditystoreService;
	}

	@ResponseBody
	@RequestMapping("getCommodityStoreByStoreNo.do")
	public ResultHelper getCommdityByStoreNo(String storeNo) {
		ResultHelper result = commoditystoreService.getCommodityByStoreNo(storeNo);
		return result;
		// commoditystore/getCommodityStoreByStoreNo.do?storeNo=1004
	}
	@ResponseBody
	@RequestMapping("updateCommodityStore.do")
	public ResultHelper updateCommodityStore(CommodityStore commoditystore){
	ResultHelper result=commoditystoreService.updateCommodityStore(commoditystore);
	return result;
	//commoditystore/updateCommodityStore.do
	}
}
