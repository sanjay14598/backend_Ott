package com.gamotrance.OTT.Model;

public class ChatXY {
private String days;
public String getDays() {
	return days;
}
public ChatXY() {
	super();
}
public int getUserCount() {
	return userCount;
}
public void setUserCount(int userCount) {
	this.userCount = userCount;
}
public ChatXY(String days, int userCount) {
	super();
	this.days = days;
	this.userCount = userCount;
}
public void setDays(String days) {
	this.days = days;
}
private int userCount;
}
