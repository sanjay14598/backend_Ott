package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.OtpRepo;
import com.gamotrance.OTT.Model.AppUpdate;
import com.gamotrance.OTT.Model.Otp;
import com.gamotrance.OTT.Model.User;


@Repository
public class OtpServices {
	@Autowired
	OtpRepo otpRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	public Otp addAppUpdate(Otp contVideo)
	 {
		return mongoTemplate.save(contVideo);
	 }
	
	public boolean getValidate(String mobile, String otp) {
        Query query = new Query();
        query.addCriteria(Criteria.where("mobile").is(mobile).andOperator(Criteria.where("otp").is(otp)));
        Otp userTest2 = mongoTemplate.findOne(query, Otp.class);
        System.out.println("userTest2 - " + userTest2);
        // TODO Auto-generated method stub
        if(userTest2==null)
        {
        	return false;
        }else 
        {
        	return true;
        }
        
    }
	public List<Otp> getOtpList()
	{
		return mongoTemplate.findAll(Otp.class);
	}
	

}
