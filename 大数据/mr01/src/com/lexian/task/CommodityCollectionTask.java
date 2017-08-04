package com.lexian.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.lexian.bean.Commodity;
import com.lexian.bean.CommodityStatistics;
import com.lexian.statistics.mapper.CommodityMapper;
import com.lexian.statistics.reducer.CommodityRecuder;

public class CommodityCollectionTask extends BaseTask{

	
	
	public CommodityCollectionTask(){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		super.outputTable="commodity_collection_statistics";
		super.inputClass=Commodity.class;
		super.outputClass=CommodityStatistics.class;
		super.querySql="select commodity_no,collecttime as time from commodity_collection " +
				"where collecttime>'"+format.format(getPreYear())+"'";
		super.countSql="select count(*) from commodity_collection where collecttime>'"+format.format(getPreYear())+"'";
		super.mapperClass=CommodityMapper.class;
		super.recuderClass=CommodityRecuder.class;
		String[] fields={"commodity_no","type","count"};
		super.outputFields=fields;
	}
	

}
