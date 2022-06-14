package com.gamotrance.OTT.Model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.gamotrance.OTT.Model.*;

@Document
public class Wallet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private Long amount;
    public Wallet() {
		super();
	}
	public Wallet(int id, Long amount, int userId, List<Transc> tran) {
		super();
		this.id = id;
		this.amount = amount;
		this.userId = userId;
		this.tran = tran;
	}
	private int userId;
    private List<Transc> tran;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Transc> getTran() {
		return tran;
	}
	public void setTran(List<Transc> tran) {
		this.tran = tran;
	}
}
