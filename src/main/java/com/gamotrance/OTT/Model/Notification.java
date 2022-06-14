package com.gamotrance.OTT.Model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
	private String topic;
	private String content;
	private String banner;
	private String title; 
	public Notification(int id, String topic, String content, String banner, String title, LocalDateTime timestamp,
			NotificationType notifTYpe) {
		super();
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.banner = banner;
		this.title = title;
		this.timestamp = timestamp;
		this.notifTYpe = notifTYpe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public NotificationType getNotifTYpe() {
		return notifTYpe;
	}
	public void setNotifTYpe(NotificationType notifTYpe) {
		this.notifTYpe = notifTYpe;
	}
	private LocalDateTime timestamp;
	private NotificationType notifTYpe; 

}
