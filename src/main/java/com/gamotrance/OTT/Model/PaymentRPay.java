package com.gamotrance.OTT.Model;

public class PaymentRPay {
	private String apikey;
	private String secKey;
	private String paymentid;
	private Integer amount;
	public PaymentRPay() {
		super();
	}
	public PaymentRPay(String apikey, String secKey, String paymentid, Integer amount, String cur, int userId,
			String vdoId) {
		super();
		this.apikey = apikey;
		this.secKey = secKey;
		this.paymentid = paymentid;
		this.amount = amount;
		this.cur = cur;
		this.userId = userId;
		this.vdoId = vdoId;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	public String getVdoId() {
		return vdoId;
	}
	public void setVdoId(String vdoId) {
		this.vdoId = vdoId;
	}
	private String cur;
	private int userId;
	private String vdoId;

}
