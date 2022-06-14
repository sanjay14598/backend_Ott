package com.gamotrance.OTT.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
@Document
public class DBanner { 
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 private String url;
 private BannerType bannerType;
 public DBanner() {
	super();
}
public DBanner(int id, String url, BannerType bannerType, LocalDate vilidity, String bannerName) {
	super();
	this.id = id;
	this.url = url;
	this.bannerType = bannerType;
	this.vilidity = vilidity;
	this.bannerName = bannerName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public BannerType getBannerType() {
	return bannerType;
}
public void setBannerType(BannerType bannerType) {
	this.bannerType = bannerType;
}
public LocalDate getVilidity() {
	return vilidity;
}
public void setVilidity(LocalDate vilidity) {
	this.vilidity = vilidity;
}
public String getBannerName() {
	return bannerName;
}
public void setBannerName(String bannerName) {
	this.bannerName = bannerName;
}
@Column(name = "vilidity", nullable=false, updatable = false)
 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
 private LocalDate vilidity;
 private String bannerName;
}
