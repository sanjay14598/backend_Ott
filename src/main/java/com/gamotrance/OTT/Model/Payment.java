package com.gamotrance.OTT.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Document
public class Payment {
	 public Payment() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	
	public Payment(int id, @NotNull String amount, String subscriptionType, LocalDate createdAt,
			LocalDate paymentDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.subscriptionType = subscriptionType;
		this.createdAt = createdAt;
		this.paymentDate = paymentDate;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    int id;
	    @NotNull
	    private String amount;
	    private String subscriptionType;
	    @Column(name = "createdAt", nullable=false, updatable = false)
		 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
	    private LocalDate createdAt;
	    @Column(name = "paymentDate", nullable=false, updatable = false)
		 @DateTimeFormat(pattern = "dd-MMM-yyyy")
	    private LocalDate paymentDate;
	   

}
