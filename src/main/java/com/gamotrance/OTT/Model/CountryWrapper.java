package com.gamotrance.OTT.Model;

import java.util.List;

public class CountryWrapper {
List<String> countList;

public CountryWrapper(List<String> countList) {
	super();
	this.countList = countList;
}

public List<String> getCountList() {
	return countList;
}

public void setCountList(List<String> countList) {
	this.countList = countList;
}

public CountryWrapper() {
	super();
}
}
