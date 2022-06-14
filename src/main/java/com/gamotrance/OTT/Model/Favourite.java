package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favourite {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
	@NotNull
    private int vodId;
	public Favourite() {
		super();
	}
	public Favourite(int id, @NotNull int vodId, @NotNull int userId) {
		super();
		this.id = id;
		this.vodId = vodId;
		this.userId = userId;
	}
	@NotNull
    private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVodId() {
		return vodId;
	}
	public void setVodId(int vodId) {
		this.vodId = vodId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
