package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Likes {
	public Likes(int id, String userId, String videoId) {
		super();
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
	private String userId;
	private String videoId;
	private String likes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public Likes(int id, String userId, String videoId, String likes) {
		super();
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
		this.likes = likes;
	}
	public Likes() {
		// TODO Auto-generated constructor stub
	}


}
