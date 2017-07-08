package com.lexian.manager.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexian.manager.goods.bean.Commodity;
import com.lexian.manager.goods.service.CommodityService;
import com.lexian.web.ResultHelper;

@Controller
@RequestMapping("commodity")
@SessionAttributes(value={"isLogining"},types={Boolean.class})
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	


	public CommodityService getCommodityService() {
		return commodityService;
	}

	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	
	@ResponseBody
	@RequestMapping("getCommodities.do")
	public ResultHelper getCommodities(){
		
		ResultHelper result= commodityService.getCommodities();
		//commodity/getCommodities.do
		return result;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("getCommodityByName.do")
	public ResultHelper getCommodityByName(String name){
		ResultHelper result=commodityService.getCommodityByName(name);
		
		return result;
		//commodity/getCommodityByName.do?name=珍欣精选野生花菇400g
	}
	
	@ResponseBody
	@RequestMapping("getCommodityBycommodityNo.do")
	public ResultHelper getCommodityBycommodityNo(String commodityNo){
		
		ResultHelper result=commodityService.getCommodityBycommodityNo(commodityNo);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("getCommodityById.do")
	public ResultHelper getCommodityById(int id){
		ResultHelper result=commodityService.getCommodityById(id);
		return result;
		//commodity/getCommodityById.do?id=40
	}
	
	@ResponseBody
	@RequestMapping("addCommodity.do")
	public ResultHelper addCommodity(Commodity commodity){
		//Commodity commodity=new Commodity();
		
		
		ResultHelper result=commodityService.addCommodity(commodity);
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("updateCommodity.do")
	public ResultHelper updateCommodity(int id,Commodity commodity){
		ResultHelper result=commodityService.updateCommodity(id,commodity);
		return result;
		
	}
}
