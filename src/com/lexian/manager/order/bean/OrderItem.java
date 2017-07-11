package com.lexian.manager.order.bean;

import com.lexian.manager.goods.bean.Commodity;

public class OrderItem {

	private Integer id;
	private Integer orderId;
	private String commodityNo;
	private Integer amount;
	private Double listPrice;
	private Double totalPrice;
	
	private Commodity commodity;
	
	
	
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", commodityNo=" + commodityNo + ", amount=" + amount
				+ ", listPrice=" + listPrice + ", totalPrice=" + totalPrice + ", commodity=" + commodity + "]";
	}
	
}
