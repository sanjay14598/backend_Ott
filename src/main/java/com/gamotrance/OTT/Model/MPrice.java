package com.gamotrance.OTT.Model;

public class MPrice {
	private int mPrice;
	public MPrice() {
		super();
	}
	public MPrice(int mPrice, int tPrice, int yPrice) {
		super();
		this.mPrice = mPrice;
		this.tPrice = tPrice;
		this.yPrice = yPrice;
	}
	public int getmPrice() {
		return mPrice;
	}
	public void setmPrice(int mPrice) {
		this.mPrice = mPrice;
	}
	public int gettPrice() {
		return tPrice;
	}
	public void settPrice(int tPrice) {
		this.tPrice = tPrice;
	}
	public int getyPrice() {
		return yPrice;
	}
	public void setyPrice(int yPrice) {
		this.yPrice = yPrice;
	}
	private int tPrice;
	private int yPrice;
}
