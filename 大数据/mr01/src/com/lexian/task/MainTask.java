package com.lexian.task;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainTask {
	
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor pool=new ScheduledThreadPoolExecutor(1); 
		
		BaseTask commodityBrowseTask=new CommodityBrowseTask();
		
		BaseTask commodityBuyTask=new CommodityBuyTask();
		
		BaseTask commodityCollectionTask=new CommodityCollectionTask();
		
		BaseTask storeCommodityBuyTask=new StoreCommodityBuyTask();
		
		BaseTask storeCommodityCollectionTask=new StoreCommodityCollectionTask();
		
		pool.scheduleAtFixedRate(commodityBrowseTask, 0,1, TimeUnit.HOURS);
		pool.scheduleAtFixedRate(commodityBuyTask, 0,1, TimeUnit.HOURS);
		pool.scheduleAtFixedRate(commodityCollectionTask, 0,1, TimeUnit.HOURS);
		pool.scheduleAtFixedRate(storeCommodityBuyTask, 0,1, TimeUnit.HOURS);
		pool.scheduleAtFixedRate(storeCommodityCollectionTask, 0,1, TimeUnit.HOURS);
		
	}
}
