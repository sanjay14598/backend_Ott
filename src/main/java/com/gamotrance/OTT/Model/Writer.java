package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Writer implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String writerName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Writer() {
		super();
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public Writer(int id, @NotNull String writerName) {
		super();
		this.id = id;
		this.writerName = writerName;
	}
	

}
