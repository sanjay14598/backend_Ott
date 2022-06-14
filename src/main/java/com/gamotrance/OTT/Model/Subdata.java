package com.gamotrance.OTT.Model;

public class Subdata {
	private int id;
	public Subdata(int id, String mobile, int duration) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String mobile;
	private int duration;
	public String getMobile() {
		return mobile;
	}
	public Subdata() {
		super();
	}
	public Subdata(String mobile, int duration) {
		super();
		this.mobile = mobile;
		this.duration = duration;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
