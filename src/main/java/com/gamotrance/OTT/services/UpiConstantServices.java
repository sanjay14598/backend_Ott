package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.UpiConstantRepo;
import com.gamotrance.OTT.Model.PaymentMethod;
import com.gamotrance.OTT.Model.UpiConstant;
@Repository
public class UpiConstantServices {

	@Autowired
	UpiConstantRepo  upiConstantRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public UpiConstant addUpiConstant(UpiConstant help) {
		// TODO Auto-generated method stub
//help.setId((LocalDateTime.now().toString()).hashCode());
		return upiConstantRepo.save(help);
	}


	public List<UpiConstant> getUpiConstantAll() {
		// TODO Auto-generated method stub
		
		return upiConstantRepo.findAll();
	}


	public List<UpiConstant> getByListUpiConstant(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<UpiConstant> channel = mongoTemplate.find(query, UpiConstant.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

//	@Override
//	public List<Plan> getPlanById(int id) {
//		// TODO Auto-generated method stub
//		Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//        List<Help> channel = mongoTemplate.find(query, Help.class);
//        System.out.println("userTest2 - " + channel);
//		return channel;
//	}
	public UpiConstant updateUpiConstant(UpiConstant plan)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("id").is(plan.getId()));
       UpiConstant channel = mongoTemplate.findOne(query, UpiConstant.class);
       if(channel!=null)
       {
       	return upiConstantRepo.save(plan);
       }
       else
       {
    	   return null;
       }
//		 return getContVideoList(userId);
	 }
	
	public boolean deleteUpiConstant(UpiConstant help) {
		// TODO Auto-generated method stub
		upiConstantRepo.delete(help);
		
		return false;
	}
	public boolean deleteAllUpiConstant() {
		// TODO Auto-generated method stub
		upiConstantRepo.deleteAll();
		
		return false;
	}


	public UpiConstant getUpiConstantByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        UpiConstant channel = mongoTemplate.findOne(query, UpiConstant.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}
	
}
