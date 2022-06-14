package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cast implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String name;
    @NotNull
    private String imageUrl;
	public Cast() {
		super();
	}
	public Cast(int id, @NotNull String name, @NotNull String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
