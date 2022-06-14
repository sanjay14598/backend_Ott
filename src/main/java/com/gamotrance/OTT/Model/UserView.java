package com.gamotrance.OTT.Model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
@Document
public class UserView {
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
    private String userId;
    private String videoId;
   
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public LocalDate getStartTimeStamp() {
		return startTimeStamp;
	}
	public void setStartTimeStamp(LocalDate startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}
	public LocalDate getEndTimeStamp() {
		return endTimeStamp;
	}
	public void setEndTimeStamp(LocalDate endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm:ss")
	private LocalDate startTimeStamp;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDate endTimeStamp;
	
	
	public UserView(Integer id, String userId, String videoId, LocalDate startTimeStamp, LocalDate endTimeStamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
		this.startTimeStamp = startTimeStamp;
		this.endTimeStamp = endTimeStamp;
	}
	public UserView() {
		super();
	}
}
