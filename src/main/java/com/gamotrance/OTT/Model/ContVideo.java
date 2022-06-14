package com.gamotrance.OTT.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class ContVideo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
	@NotNull
    private String vodId;
    @NotNull
    private String lastTime; 
    
    private String thumb;
    private String ageGroup;
    public ContVideo() {
		super();
	}
	private String vdoUrl;
    private String title;
	public ContVideo(int id, @NotNull String vodId, @NotNull String lastTime, String thumb, String ageGroup,
			String vdoUrl, String title, List<Thumb> mThumbs, String duration, String userId, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.vodId = vodId;
		this.lastTime = lastTime;
		this.thumb = thumb;
		this.ageGroup = ageGroup;
		this.vdoUrl = vdoUrl;
		this.title = title;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.userId = userId;
		this.timeStamp = timeStamp;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getVdoUrl() {
		return vdoUrl;
	}
	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private List<Thumb> mThumbs;
    private String duration;
    public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public List<Thumb> getmThumbs() {
		return mThumbs;
	}
	public ContVideo(int id, @NotNull String vodId, @NotNull String lastTime, String thumb, List<Thumb> mThumbs,
			String duration, String userId, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.vodId = vodId;
		this.lastTime = lastTime;
		this.thumb = thumb;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.userId = userId;
		this.timeStamp = timeStamp;
	}
	public void setmThumbs(List<Thumb> mThumbs) {
		this.mThumbs = mThumbs;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	private String userId;
    @Column(name = "createdAt", nullable=false, updatable = false)
  	@DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
	
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
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

}
