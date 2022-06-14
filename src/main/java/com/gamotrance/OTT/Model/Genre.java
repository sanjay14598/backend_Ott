package com.gamotrance.OTT.Model;

import java.io.Serializable;

public class Genre implements Serializable{

    private String genreName;
	public Genre() {
		super();
	}
	
	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}

	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

}
