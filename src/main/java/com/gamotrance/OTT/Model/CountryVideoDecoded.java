package com.gamotrance.OTT.Model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CountryVideoDecoded {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	   private  int id;
	 public CountryVideoDecoded() {
		super();
	}
	public CountryVideoDecoded(int id, List<String> countrylist) {
		super();
		this.id = id;
		this.countrylist = countrylist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getCountrylist() {
		return countrylist;
	}
	public void setCountrylist(List<String> countrylist) {
		this.countrylist = countrylist;
	}
	private List<String> countrylist;
}
