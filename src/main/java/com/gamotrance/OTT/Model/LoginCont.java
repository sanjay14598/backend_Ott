package com.gamotrance.OTT.Model;

public class LoginCont {
	private String userName;
	private String password;
	public LoginCont() {
		super();
	}
	public LoginCont(String userName, String password, Device device) {
		super();
		this.userName = userName;
		this.password = password;
		this.device = device;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	private Device device;

}
