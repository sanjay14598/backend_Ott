package com.gamotrance.OTT.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Transc {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    public Transc() {
		super();
	}
	@NotNull
    private Long amount;
    public Transc(int id, @NotNull Long amount, TranscationType transcationType, LocalDate createdAt, String purpose,
			String title) {
		super();
		this.id = id;
		this.amount = amount;
		this.transcationType = transcationType;
		this.createdAt = createdAt;
		this.purpose = purpose;
		this.title = title;
	}
	private TranscationType transcationType;
    @Column(name = "createdAt", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDate createdAt;
    private String purpose;
	private String title;
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
	public TranscationType getTranscationType() {
		return transcationType;
	}
	public void setTranscationType(TranscationType transcationType) {
		this.transcationType = transcationType;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 

}
