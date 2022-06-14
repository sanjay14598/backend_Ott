package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.TransctionFieldRepo;
import com.gamotrance.OTT.Model.PaymentMethod;
import com.gamotrance.OTT.Model.TransctiionField;

@Repository
public class TranscationFieldServices {
	
	@Autowired
	TransctionFieldRepo transctionFieldRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public TransctiionField addTransctiionField(TransctiionField help) {
		// TODO Auto-generated method stub
help.setId((help.getId()+""+LocalDateTime.now().toString()).hashCode());
help.setLastUpdateTime(LocalDateTime.now());
		return transctionFieldRepo.save(help);
	}


	public List<TransctiionField> getTransctiionFieldAll() {
		// TODO Auto-generated method stub
		
		return transctionFieldRepo.findAll();
	}


	public List<TransctiionField> getByListTransctiionField(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<TransctiionField> channel = mongoTemplate.find(query, TransctiionField.class);
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
	public TransctiionField updateTransctiionField(TransctiionField plan)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("id").is(plan.getId()));
       TransctiionField channel = mongoTemplate.findOne(query, TransctiionField.class);
       if(channel!=null)
       {
       	return transctionFieldRepo.save(plan);
       }
       else
       {
    	   return null;
       }
//		 return getContVideoList(userId);
	 }
	
	public boolean deleteTransctiionField(TransctiionField help) {
		// TODO Auto-generated method stub
		transctionFieldRepo.delete(help);
		
		return false;
	}
	public boolean deleteAllTransctiionField() {
		// TODO Auto-generated method stub
		transctionFieldRepo.deleteAll();
		
		return false;
	}


	public TransctiionField getTransctiionFieldByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        TransctiionField channel = mongoTemplate.findOne(query, TransctiionField.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

}
