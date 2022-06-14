package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RentAccess implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String vdoId;
    private String userId;
    private String expDate;
	public RentAccess() {
		super();
	}
	public RentAccess(int id, @NotNull String vdoId, String userId, String expDate) {
		super();
		this.id = id;
		this.vdoId = vdoId;
		this.userId = userId;
		this.expDate = expDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVdoId() {
		return vdoId;
	}
	public void setVdoId(String vdoId) {
		this.vdoId = vdoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	

}
