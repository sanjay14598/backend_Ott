package com.gamotrance.OTT.Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class VatualWallet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;
    private Long totalAmount;
	private List<Payment> payments;
    private String tranctionType;
    @Column(name = "createdAt", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDate createdAt;

}
