package com.gamotrance.OTT.Model;

public class ResultInfo {
	
	private String resultStatus;
	private String  resultCode;
	private String resultMsg;
	public ResultInfo() {
		super();
	}
	private String bankRetry;
	private String retry;
	public ResultInfo(String resultStatus, String resultCode, String resultMsg, String bankRetry, String retry) {
		super();
		this.resultStatus = resultStatus;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.bankRetry = bankRetry;
		this.retry = retry;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getBankRetry() {
		return bankRetry;
	}
	public void setBankRetry(String bankRetry) {
		this.bankRetry = bankRetry;
	}
	public String getRetry() {
		return retry;
	}
	public void setRetry(String retry) {
		this.retry = retry;
	}

}
