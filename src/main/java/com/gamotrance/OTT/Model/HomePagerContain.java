package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class HomePagerContain {
	public HomePagerContain() {
		super();
	}
	public HomePagerContain(int id, @NotNull String expDate, @NotNull String subDate, String likes, int videoId) {
		super();
		this.id = id;
		this.expDate = expDate;
		this.subDate = subDate;
		this.likes = likes;
		this.videoId = videoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getSubDate() {
		return subDate;
	}
	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String expDate;
    @NotNull
    private String subDate;
    private String likes;
    private int videoId;

}
