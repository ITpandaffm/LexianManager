package com.lexian.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.lexian.bean.Commodity;
import com.lexian.bean.CommodityStatistics;
import com.lexian.statistics.mapper.CommodityMapper;
import com.lexian.statistics.reducer.CommodityRecuder;


public class CommodityBrowseTask extends BaseTask{

	public CommodityBrowseTask(){
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		super.outputTable="commodity_browse_statistics";
		super.inputClass=Commodity.class;
		super.outputClass=CommodityStatistics.class;
		super.querySql="SELECT commodity_no,browsetime AS TIME FROM browse " +
				"where browsetime>'"+format.format(getPreYear())+"'";
		super.countSql="select count(*) from browse WHERE browsetime>'"+format.format(getPreYear())+"'";
		super.mapperClass=CommodityMapper.class;
		super.recuderClass=CommodityRecuder.class;
		String[] fields={"commodity_no","type","count"};
		super.outputFields=fields;
	}
	
}
