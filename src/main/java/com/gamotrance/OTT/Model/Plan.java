package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Plan implements Serializable{
	
	public Plan() {
		super();
	}
	public Plan(int id, String planName, String planPrice, String plandescription, String planDuration) {
		super();
		this.id = id;
		this.planName = planName;
		this.planPrice = planPrice;
		this.plandescription = plandescription;
		this.planDuration = planDuration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanPrice() {
		return planPrice;
	}
	public void setPlanPrice(String planPrice) {
		this.planPrice = planPrice;
	}
	public String getPlandescription() {
		return plandescription;
	}
	public void setPlandescription(String plandescription) {
		this.plandescription = plandescription;
	}
	public String getPlanDuration() {
		return planDuration;
	}
	public void setPlanDuration(String planDuration) {
		this.planDuration = planDuration;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String planName;
    private String planPrice;
    public Plan(int id, String planName, String planPrice, String plandescription, String planDuration,
			UserType planType) {
		super();
		this.id = id;
		this.planName = planName;
		this.planPrice = planPrice;
		this.plandescription = plandescription;
		this.planDuration = planDuration;
		this.planType = planType;
	}
	public UserType getPlanType() {
		return planType;
	}
	public void setPlanType(UserType planType) {
		this.planType = planType;
	}
	private String plandescription;
    private String planDuration;
    private UserType planType;
}
