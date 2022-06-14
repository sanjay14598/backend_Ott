package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class VideoCatList implements Serializable{
	@Override
	public String toString() {
		return "VideoCatList [id=" + id + ", list=" + list + ", msg=" + msg + ", responseCode=" + responseCode + "]";
	}

	public VideoCatList(int id, List<CatagoryResponse> list, String msg, String responseCode) {
		super();
		this.id = id;
		this.list = list;
		this.msg = msg;
		this.responseCode = responseCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;
private List<CatagoryResponse> list;
private String msg;
public VideoCatList() {
	super();
}

public VideoCatList(List<CatagoryResponse> list, String msg, String responseCode) {
	super();
	this.list = list;
	this.msg = msg;
	this.responseCode = responseCode;
}

public List<CatagoryResponse> getList() {
	return list;
}
public void setList(List<CatagoryResponse> list) {
	this.list = list;
}

private String responseCode;
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getResponseCode() {
	return responseCode;
}
public void setResponseCode(String responseCode) {
	this.responseCode = responseCode;
}
}
