package com.gamotrance.OTT.Model;

public class AuthBody {

	public AuthBody(String email, String password) {
	
		this.email = email;
		this.password = password;
	}
	
	public AuthBody() {
		super();
	}

	private String email;
    private String password;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
