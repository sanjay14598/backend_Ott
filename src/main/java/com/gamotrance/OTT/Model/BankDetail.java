package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class BankDetail  {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
   
public BankDetail() {
		super();
	}
public BankDetail(int id, String bankName, String branchName, String accnumber, String ifsc, int userId) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accnumber = accnumber;
		this.ifsc = ifsc;
		this.userId = userId;
	}
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccnumber() {
		return accnumber;
	}
	public void setAccnumber(String accnumber) {
		this.accnumber = accnumber;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
private String bankName;
private String branchName;
private String accnumber;
private String ifsc;
private int userId;
}
