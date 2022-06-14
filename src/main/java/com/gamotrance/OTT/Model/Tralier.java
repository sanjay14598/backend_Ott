package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tralier implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String tralierThumb;
	private String tralierUrl;
	private String duaration;
	private String submitDate;
	public Tralier() {
		super();
	}
	public Tralier(int id, @NotNull String tralierThumb, String tralierUrl, String duaration, String submitDate) {
		super();
		this.id = id;
		this.tralierThumb = tralierThumb;
		this.tralierUrl = tralierUrl;
		this.duaration = duaration;
		this.submitDate = submitDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTralierThumb() {
		return tralierThumb;
	}
	public void setTralierThumb(String tralierThumb) {
		this.tralierThumb = tralierThumb;
	}
	public String getTralierUrl() {
		return tralierUrl;
	}
	public void setTralierUrl(String tralierUrl) {
		this.tralierUrl = tralierUrl;
	}
	public String getDuaration() {
		return duaration;
	}
	public void setDuaration(String duaration) {
		this.duaration = duaration;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
}
