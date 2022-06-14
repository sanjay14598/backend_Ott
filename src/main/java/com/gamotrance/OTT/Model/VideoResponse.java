package com.gamotrance.OTT.Model;

import java.util.*;

public class VideoResponse {
	private String errorMessage;
	private String responseCode;
	public VideoResponse() {
		super();
	}
	private List<SingleVideo> list;
	public VideoResponse(String errorMessage, String responseCode, List<SingleVideo> list) {
		super();
		this.errorMessage = errorMessage;
		this.responseCode = responseCode;
		this.list = list;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public List<SingleVideo> getList() {
		return list;
	}
	public void setList(List<SingleVideo> list) {
		this.list = list;
	}

}
