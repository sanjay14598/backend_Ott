package com.gamotrance.OTT.Model;

public class CashFreeRequest {
	
	private String orderId;
	private String orderAmount;
	private String orderCurrency;
	
	
	public CashFreeRequest(String orderId, String orderAmount, String orderCurrency) {
		super();
		this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.orderCurrency = orderCurrency;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	
	
	
	

}
