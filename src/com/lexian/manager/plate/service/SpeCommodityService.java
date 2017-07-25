package com.lexian.manager.plate.service;

import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

public interface SpeCommodityService {
	
	public ResultHelper deleteSpeCommodity(int id);
	
	public ResultHelper getSpecialCommodities(int id,Page page);
	
	public ResultHelper addSpecialCommodities(String commodityNo,int id);
}
