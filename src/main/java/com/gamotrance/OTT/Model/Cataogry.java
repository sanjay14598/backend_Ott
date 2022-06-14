package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Cataogry implements Serializable {

    @NotNull
     String peer;
     BoxType boxType;
	public Cataogry(@NotNull String peer, BoxType boxType) {
		super();
		this.peer = peer;
		this.boxType = boxType;
	}

	public BoxType getBoxType() {
		return boxType;
	}

	public void setBoxType(BoxType boxType) {
		this.boxType = boxType;
	}

	public Cataogry() {
		super();
	}

	public Cataogry(@NotNull String peer) {
		super();
		this.peer = peer;
	}

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}
}
