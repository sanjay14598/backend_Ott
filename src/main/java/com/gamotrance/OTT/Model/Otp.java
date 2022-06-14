package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Otp {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    public Otp() {
		super();
	}
	public Otp(int id, @NotNull String otp, @NotNull String mobile) {
		super();
		this.id = id;
		this.otp = otp;
		this.mobile = mobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@NotNull
    private String otp;
    @NotNull
    private String mobile;
}
