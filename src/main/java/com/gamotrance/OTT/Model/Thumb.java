package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Thumb implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String thumbSize;
	private String thumb;
	public Thumb() {
		super();
	}
	private BoxType bt;
    public Thumb(int id, @NotNull String thumbSize, String thumb, BoxType bt) {
		super();
		this.id = id;
		this.thumbSize = thumbSize;
		this.thumb = thumb;
		this.bt = bt;
	}

	public BoxType getBt() {
		return bt;
	}

	public void setBt(BoxType bt) {
		this.bt = bt;
	}

	public Thumb(int id, @NotNull String thumbSize, String thumb) {
		super();
		this.id = id;
		this.thumbSize = thumbSize;
		this.thumb = thumb;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThumbSize() {
		return thumbSize;
	}
	public void setThumbSize(String thumbSize) {
		this.thumbSize = thumbSize;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
}
