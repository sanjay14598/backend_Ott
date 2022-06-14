package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class RemindMe {
	public RemindMe() {
		super();
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String vodId;
    private String userId;
	public RemindMe(int id, @NotNull String vodId, String userId) {
		super();
		this.id = id;
		this.vodId = vodId;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVodId() {
		return vodId;
	}
	public void setVodId(String vodId) {
		this.vodId = vodId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
