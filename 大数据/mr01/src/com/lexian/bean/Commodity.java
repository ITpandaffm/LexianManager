package com.lexian.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

public class Commodity implements Writable, DBWritable{ 
	
	private String commodityNo;
	private Date time;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date browseTime) {
		this.time = browseTime;
	}
	
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	
	@Override
	public void readFields(ResultSet rs) throws SQLException {
		this.commodityNo=rs.getString("commodity_no");
		this.time=rs.getDate("time");
	}
	@Override
	public void write(PreparedStatement pst) throws SQLException {
		pst.setString(1, commodityNo);
		pst.setDate(2, time);
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.commodityNo=Text.readString(in);
		this.time = Date.valueOf(Text.readString(in));
	}
	@Override
	public void write(DataOutput out) throws IOException {
		Text.writeString(out,commodityNo);
		Text.writeString(out,this.time.toString()) ;
	}
	@Override
	public String toString() {
		return "CommodityBrowseStatistics [commodityNo="
				+ commodityNo + ", browseTime=" + time + "]";
	}
	
	
}
