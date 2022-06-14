package com.gamotrance.OTT.Model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Channel {
	public Channel() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private String profileUrl;
    private List<String> subscribers;
    private List<Genre> genres;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	public Channel(int id, @NotNull String title, @NotNull String description, String profileUrl,
			List<String> subscribers, List<Genre> genres) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.profileUrl = profileUrl;
		this.subscribers = subscribers;
		this.genres = genres;
	}
	public List<String> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(List<String> subscribers) {
		this.subscribers = subscribers;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

}
