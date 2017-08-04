package com.lexian.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.lexian.bean.Store;
import com.lexian.bean.StoreStatistics;
import com.lexian.statistics.mapper.StoreMapper;
import com.lexian.statistics.reducer.StoreRecuder;

public class StoreCommodityBuyTask  extends BaseTask{
	
	
	public StoreCommodityBuyTask(){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		super.outputTable="store_commodity_buy_statistics";
		super.inputClass=Store.class;
		super.outputClass=StoreStatistics.class;
		super.querySql="select store_no,commodity_no,createtime as time from orders,orderitem " +
				"where orders.id=orderitem.order_id AND createtime>'"+format.format(getPreYear())+"'";
		super.countSql="select count(*) from orders,orderitem WHERE orders.id=orderitem.order_id AND createtime>'"+format.format(getPreYear())+"'";
		super.mapperClass=StoreMapper.class;
		super.recuderClass=StoreRecuder.class;
		String[] fields={"store_no","commodity_no","type","count"};
		super.outputFields=fields;
	}
	

}
