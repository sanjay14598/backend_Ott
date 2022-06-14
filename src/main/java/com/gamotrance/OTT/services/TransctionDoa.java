package com.gamotrance.OTT.services;

import com.gamotrance.OTT.Model.Transcation;

public interface TransctionDoa {
	Transcation getTranctionById(String userid);
	Transcation addTranction(Transcation transcation);
	String deleteTranction(Transcation transcation);


}
