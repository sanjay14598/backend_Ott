package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.TransctionRepo;
import com.gamotrance.OTT.Doa.UserRepo;
import com.gamotrance.OTT.Model.Payment;
import com.gamotrance.OTT.Model.Transcation;
import com.gamotrance.OTT.Model.User;
@Repository
public class TransctionSErvices implements TransctionDoa{
	 @Autowired
	    TransctionRepo transctionRepo;
	    @Autowired
	    MongoTemplate mongoTemplate;
	

	@Override
	public Transcation getTranctionById(String userid) {
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userid));
        Transcation transcation = mongoTemplate.findOne(query, Transcation.class);
        
		return transcation;
	}

	public List<Transcation> getAllTranction() {
//		Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userid));
//        Transcation transcation = mongoTemplate.findOne(query, Transcation.class);
        
		return transctionRepo.findAll();
	}

	@Override
	public Transcation addTranction(Transcation transcation) {
		//transcation.setId(transcation.getBANKTXNID().hashCode());
		// TODO Auto-generated method stub
		return transctionRepo.save(transcation);
	}

	@Override
	public String deleteTranction(Transcation transcation) {
		// TODO Auto-generated method stub
		transctionRepo.delete(transcation);
		return null;
	}

}
