package com.lexian.statistics.mapper;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.lexian.bean.Commodity;

public class CommodityMapper extends Mapper<LongWritable, Commodity, Text,NullWritable >{
	
	private Date current=new Date(new java.util.Date().getTime());
	private Text mKey=new Text();
	@Override
    protected void map(LongWritable key, Commodity value, Context context)
            throws IOException, InterruptedException {
        
		Date time=value.getTime();
		
		int type=getTypeByTime(time);
		String no=value.getCommodityNo();
		switch(type){
			case 1:
				writeToReducer(context,1,no);
				writeToReducer(context,2,no);
				writeToReducer(context,3,no);
				break;
			case 2:
				writeToReducer(context,2,no);
				writeToReducer(context,3,no);
				break;
			case 3:
				writeToReducer(context,3,no);
				break;
		}
		writeToReducer(context,4,no);
    }


	private void writeToReducer(Context context,
			int type,String no) throws IOException, InterruptedException {
		mKey.set(type+"_"+no);
		context.write(mKey, NullWritable.get());
	}


	/**
	* 获取两个日期相差的月数
	* @param d1  较大的日期
	* @param d2  较小的日期
	* @return 如果d1>d2返回 月数差 否则返回0
	*/
	public int getMonthDiff(Date d1, Date d2) {
	    Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if(month1 < month2 || month1 == month2 && day1 < day2){
			yearInterval --;
		}
		// 获取月数差值
		int monthInterval = (month1 + 12) - month2 ;
		if(day1 < day2) {
			monthInterval --;
		}
		monthInterval %= 12;
		return yearInterval * 12 + monthInterval;
	}
	
	private int getTypeByTime(Date time) {
		
		int months=getMonthDiff(time,current);
		int type=1;
		if(months<=1){
			type=1;
		}else if(months<=3){
			type=2;
		}else if(months<=6){
			type=3;
		}else{
			type=4;
		}
		return type;
	}

}
