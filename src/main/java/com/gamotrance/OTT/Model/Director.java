package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Director implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String dirName;
	public Director() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public Director(int id, @NotNull String dirName) {
		super();
		this.id = id;
		this.dirName = dirName;
	}

}
