package com.gamotrance.OTT.Model;

public class sub {
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	String id;
	String datefrom;
	String amount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatefrom() {
		return datefrom;
	}
	
	public sub(String id, String datefrom, String amount, String dateTo, String type) {
		super();
		this.id = id;
		this.datefrom = datefrom;
		this.amount = amount;
		this.dateTo = dateTo;
		this.type = type;
	}
	public sub(String id, String datefrom, String dateTo, String type) {
		super();
		this.id = id;
		this.datefrom = datefrom;
		this.dateTo = dateTo;
		this.type = type;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	String dateTo;
	String type;

}
