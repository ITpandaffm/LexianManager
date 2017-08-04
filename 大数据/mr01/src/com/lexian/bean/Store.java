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

public class Store implements Writable, DBWritable{ 
	
	private String storeNo;
	private String commodityNo;
	private Date time;
	
	
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public void readFields(ResultSet rs) throws SQLException {
		this.storeNo=rs.getString("store_no");
		this.commodityNo=rs.getString("commodity_no");
		this.time=rs.getDate("time");
	}
	@Override
	public void write(PreparedStatement pst) throws SQLException {
		pst.setString(1,storeNo);
		pst.setString(2, commodityNo);
		pst.setDate(3, time);
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.storeNo=Text.readString(in);
		this.commodityNo=Text.readString(in);
		this.time = Date.valueOf(Text.readString(in));
	}
	@Override
	public void write(DataOutput out) throws IOException {
		Text.writeString(out,storeNo);
		Text.writeString(out,commodityNo);
		Text.writeString(out,this.time.toString()) ;
	}
	
	
	
}
