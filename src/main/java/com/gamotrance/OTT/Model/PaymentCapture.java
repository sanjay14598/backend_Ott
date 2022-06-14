package com.gamotrance.OTT.Model;

public class PaymentCapture {
	
	private String apikey;
	private String secKey;
	private String paymentid;
	private Integer amount;
	private String cur;
	private int userId;
	public PaymentCapture() {
		super();
	}
	
	public PaymentCapture(String apikey, String secKey, String paymentid, Integer amount, String cur, int userId) {
		super();
		this.apikey = apikey;
		this.secKey = secKey;
		this.paymentid = paymentid;
		this.amount = amount;
		this.cur = cur;
		this.userId = userId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getSecKey() {
		return secKey;
	}
	public void setSecKey(String secKey) {
		this.secKey = secKey;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
