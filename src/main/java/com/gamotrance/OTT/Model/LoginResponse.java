package com.gamotrance.OTT.Model;

public class LoginResponse {
	
	User user;
	String token;
	String msg;
	public LoginResponse(User user, String token, String msg) {
		super();
		this.user = user;
		this.token = token;
		this.msg = msg;
	}
	public User getUser() {
		return user;
	}
	public LoginResponse() {
		super();
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
