package com.gamotrance.OTT.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class CoinTranscation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	   private  int id;
	 private String subType;
	 private CoinRedemiType coinRedemiType;
	 private String coinValue;
	 private int redimeBy;
	 private int redimeTo;
	 @Column(name = "tranctionTime", nullable=false, updatable = false)
	 @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
	 private LocalDate tranctionTime;

}
