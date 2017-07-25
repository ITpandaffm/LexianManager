package com.lexian.manager.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lexian.manager.shop.bean.CommodityStore;
import com.lexian.manager.shop.service.CommodityStoreService;
import com.lexian.web.Page;
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
	public ResultHelper getCommdityByStoreNo(Page page,String storeNo) {
		ResultHelper result = commoditystoreService.getCommodityByStoreNo(storeNo,page);
		return result;
		// commoditystore/getCommodityStoreByStoreNo.do?storeNo=1013&pageNo=10
	}
	@ResponseBody
	@RequestMapping("updateCommodityStore.do")
	public ResultHelper updateCommodityStore(CommodityStore commoditystore){
	ResultHelper result=commoditystoreService.updateCommodityStore(commoditystore);
	return result;
	//commoditystore/updateCommodityStore.do?id=8363&realPrice=11.11
	}
	@ResponseBody
	@RequestMapping("addCommodityStore.do")
	public ResultHelper addCommodityStore(List<CommodityStore> list){
		/*CommodityStore commoditystore1=new CommodityStore();
		commoditystore1.setCommodityNo("6923644242978");
		commoditystore1.setStoreNo("1001");
		commoditystore1.setType(0);
		CommodityStore commoditystore2=new CommodityStore();
		commoditystore2.setCommodityNo("6923644242961");
		commoditystore2.setStoreNo("1001");
		commoditystore2.setType(0);
		List<CommodityStore> list = new ArrayList<>();
		list.add(commoditystore1);
		list.add(commoditystore2);*/
		ResultHelper result=commoditystoreService.addCommodityStore(list);
		return result;
		//commoditystore/addCommodityStore.do
	}
	@ResponseBody
	@RequestMapping("getCommodityByCategoryId.do")
	public ResultHelper getCommodityByCategoryId(int categoryId,String storeNo) {
		ResultHelper result = commoditystoreService.getCommodityByCategoryId(categoryId,storeNo);

		return result;
		//commoditystore/getCommodityByCategoryId.do?categoryId=226&storeNo=1001
	}
	@ResponseBody
	@RequestMapping("registCommodityStore.do")
	public ResultHelper registCommodityStore(String storeNo,String[] commodityNo,Integer type) {
		/*String[] str={"6923644242961","6923644242978"};
		String storeNo="1011";
		Integer type=1;*/
		ResultHelper result=commoditystoreService.registCommodityStore(storeNo, commodityNo,type);
		return result;
		//commoditystore/registCommodityStore.do
	}
}
