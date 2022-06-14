package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "access")
public class Access implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
	private String expdate;
    @NotNull
    private int userId;
	public Access() {
		super();
	}
	public Access(int id, @NotNull String expdate, @NotNull int userId) {
		super();
		this.id = id;
		this.expdate = expdate;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpdate() {
		return expdate;
	}
	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
