package com.gamotrance.OTT.Model;

public class DashValue {
	private int count;
	private int movieCount;
	private int sortMovieCount;
	private int webCount;
	private int userCount;
	private int subCount;
	private int totalAmount;
	public DashValue() {
		super();
	}
	private int nonsubUser;
	private int subExpired;
	public DashValue(int count, int movieCount, int sortMovieCount, int webCount, int userCount, int subCount,
			int totalAmount, int nonsubUser, int subExpired) {
		super();
		this.count = count;
		this.movieCount = movieCount;
		this.sortMovieCount = sortMovieCount;
		this.webCount = webCount;
		this.userCount = userCount;
		this.subCount = subCount;
		this.totalAmount = totalAmount;
		this.nonsubUser = nonsubUser;
		this.subExpired = subExpired;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMovieCount() {
		return movieCount;
	}
	public void setMovieCount(int movieCount) {
		this.movieCount = movieCount;
	}
	public int getSortMovieCount() {
		return sortMovieCount;
	}
	public void setSortMovieCount(int sortMovieCount) {
		this.sortMovieCount = sortMovieCount;
	}
	public int getWebCount() {
		return webCount;
	}
	public void setWebCount(int webCount) {
		this.webCount = webCount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getNonsubUser() {
		return nonsubUser;
	}
	public void setNonsubUser(int nonsubUser) {
		this.nonsubUser = nonsubUser;
	}
	public int getSubExpired() {
		return subExpired;
	}
	public void setSubExpired(int subExpired) {
		this.subExpired = subExpired;
	}
}
