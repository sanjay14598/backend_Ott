package com.gamotrance.OTT.Doa;

public class CashFreeResponse {

	private String status;
	private String message;
	private String cftoken;
	
	
	public CashFreeResponse(String status, String message, String cftoken) {
		super();
		this.status = status;
		this.message = message;
		this.cftoken = cftoken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCftoken() {
		return cftoken;
	}
	public void setCftoken(String cftoken) {
		this.cftoken = cftoken;
	}
	
	
}
