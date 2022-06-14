package com.gamotrance.OTT.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UpiConstant {
	@Id
	int id;
	public UpiConstant(int id, String mId, String vpa, String appName) {
		super();
		this.id = id;
		this.mId = mId;
		this.vpa = vpa;
		this.appName = appName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String mId;
	private String vpa;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public UpiConstant() {
		super();
	}
	public UpiConstant(String mId, String vpa, String appName) {
		super();
		this.mId = mId;
		this.vpa = vpa;
		this.appName = appName;
	}
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	private String appName;

}
