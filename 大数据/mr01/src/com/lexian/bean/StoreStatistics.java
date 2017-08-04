package com.lexian.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

public class StoreStatistics  implements DBWritable,Writable{

	private String storeNo;
	private String commodityNo;
	private Integer type;
	private Integer count;
	
	
	
	

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public void readFields(ResultSet rs) throws SQLException {
		this.storeNo=rs.getString("store_no");
		this.commodityNo=rs.getString("commodity_no");
		this.type=rs.getInt("type");
		this.count=rs.getInt("count");
	}

	@Override
	public void write(PreparedStatement pst) throws SQLException {
		pst.setString(1, storeNo);
		pst.setString(2, commodityNo);
		pst.setInt(3, type);
		pst.setInt(4, count);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.storeNo=Text.readString(in);
		this.commodityNo=Text.readString(in);
		this.type=in.readInt();
		this.count=in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		Text.writeString(out, storeNo);
		Text.writeString(out, commodityNo);
		out.writeInt(type);
		out.writeInt(count);
	}

}
