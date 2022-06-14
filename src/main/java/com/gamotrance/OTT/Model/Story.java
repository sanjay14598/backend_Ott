package com.gamotrance.OTT.Model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Story {
	public Story() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public Story(int id, @NotNull String title, String topic, String contents, NotificationType notificationType,
			LocalDateTime time, String thumbnails, String likes) {
		super();
		this.id = id;
		this.title = title;
		this.topic = topic;
		this.contents = contents;
		this.notificationType = notificationType;
		this.time = time;
		this.thumbnails = thumbnails;
		this.likes = likes;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String title;
    private String topic;
    private String contents;
    private NotificationType notificationType;
    private LocalDateTime time;
    private String thumbnails;
  //  private String time;
    private String likes;
}
