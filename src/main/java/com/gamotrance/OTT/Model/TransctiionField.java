package com.gamotrance.OTT.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class TransctiionField {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	public TransctiionField() {
		super();
	}
	public TransctiionField(int id, int userId, String description, boolean status) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	private int userId;
	private String description;
	private boolean status;
	  @Column(name = "lastUpdateTime", nullable=false, updatable = false)
		 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
	    @UpdateTimestamp
	private LocalDateTime lastUpdateTime;
	private int planId;
	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public TransctiionField(int id, int userId, String description, boolean status, LocalDateTime lastUpdateTime,
			int planId) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.status = status;
		this.lastUpdateTime = lastUpdateTime;
		this.planId = planId;
	}
}
