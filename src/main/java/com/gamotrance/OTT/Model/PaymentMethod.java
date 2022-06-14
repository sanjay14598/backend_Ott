package com.gamotrance.OTT.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class PaymentMethod {
	@Id
	int id;
	public PaymentMethod() {
		super();
	}
	public PaymentMethod(int id, String paymentmethod, PaymentActiveStatus status) {
		super();
		this.id = id;
		this.paymentmethod = paymentmethod;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public PaymentActiveStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentActiveStatus status) {
		this.status = status;
	}
	private String paymentmethod;
	private PaymentActiveStatus status;
}
