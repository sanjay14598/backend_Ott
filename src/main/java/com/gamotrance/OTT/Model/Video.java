package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Video implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private String thumbs;
    private String vdoUrl;
    private String episNumber;
    private String submitDate;
	public Video(int id, @NotNull String title, @NotNull String description, String thumbs, String vdoUrl,
			String episNumber, String submitDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.thumbs = thumbs;
		this.vdoUrl = vdoUrl;
		this.episNumber = episNumber;
		this.submitDate = submitDate;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbs() {
		return thumbs;
	}
	public void setThumbs(String thumbs) {
		this.thumbs = thumbs;
	}
	public String getVdoUrl() {
		return vdoUrl;
	}
	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
	public String getEpisNumber() {
		return episNumber;
	}
	public void setEpisNumber(String episNumber) {
		this.episNumber = episNumber;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
}
