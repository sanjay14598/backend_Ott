package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

public class CatagoryResponse implements Serializable{
@Override
	public String toString() {
		return "CatagoryResponse [key=" + key + ", boxType=" + boxType + ", list=" + list + "]";
	}
private String key;
private BoxType boxType;
public BoxType getBoxType() {
	return boxType;
}
public CatagoryResponse(String key, BoxType boxType, List<DashResponse> list) {
	super();
	this.key = key;
	this.boxType = boxType;
	this.list = list;
}
public void setBoxType(BoxType boxType) {
	this.boxType = boxType;
}
private List<DashResponse> list;
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public List<DashResponse> getList() {
	return list;
}
public CatagoryResponse(String key, List<DashResponse> list) {
	super();
	this.key = key;
	this.list = list;
}
public CatagoryResponse() {
	super();
}
public void setList(List<DashResponse> list) {
	this.list = list;
}
}
