package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class DashResponse implements Serializable{
	@Override
	public String toString() {
		return "DashResponse [id=" + id + ", verticalImage=" + verticalImage + ", name=" + name + ", vdoType=" + vdoType
				+ ", mThumbs=" + mThumbs + "]";
	}
	
	int id;
	
	 @NotNull
	private String verticalImage;
	 
		private String name;
	 
		private VideoType vdoType;
	 @NotNull
	private List<Thumb> mThumbs;
	public DashResponse() {
		super();
	}

	public DashResponse(int id, @NotNull String verticalImage, String name, VideoType vdoType,
			@NotNull List<Thumb> mThumbs) {
		super();
		this.id = id;
		this.verticalImage = verticalImage;
		this.name = name;
		this.vdoType = vdoType;
		this.mThumbs = mThumbs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VideoType getVdoType() {
		return vdoType;
	}

	public void setVdoType(VideoType vdoType) {
		this.vdoType = vdoType;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVerticalImage() {
		return verticalImage;
	}
	public void setVerticalImage(String verticalImage) {
		this.verticalImage = verticalImage;
	}
	public List<Thumb> getmThumbs() {
		return mThumbs;
	}
	public void setmThumbs(List<Thumb> mThumbs) {
		this.mThumbs = mThumbs;
	}
	public DashResponse(int id, @NotNull String verticalImage, @NotNull List<Thumb> mThumbs) {
		super();
		this.id = id;
		this.verticalImage = verticalImage;
		this.mThumbs = mThumbs;
	}

}
