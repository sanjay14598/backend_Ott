package com.gamotrance.OTT.Model;

public class RentAccessPay {
	private Integer amount;
	private int userId;
	private String vdoId;
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public RentAccessPay(Integer amount, int userId, String vdoId) {
		super();
		this.amount = amount;
		this.userId = userId;
		this.vdoId = vdoId;
	}
	public RentAccessPay() {
		super();
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
}
