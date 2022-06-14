package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.PlanRepo;
import com.gamotrance.OTT.Model.Plan;


@Repository
public class PlanServices {
	
	@Autowired
	PlanRepo planRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	

	public Plan addPlan(Plan help) {
		// TODO Auto-generated method stub
help.setId((help.getId()+""+LocalDateTime.now().toString()).hashCode());
		return planRepo.save(help);
	}


	public List<Plan> getPlanAll() {
		// TODO Auto-generated method stub
		
		return planRepo.findAll();
	}


	public List<Plan> getByListPlan(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<Plan> channel = mongoTemplate.find(query, Plan.class);
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
	public Plan updatePlan(Plan plan)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("id").is(plan.getId()));
       Plan channel = mongoTemplate.findOne(query, Plan.class);
       if(channel!=null)
       {
       	return planRepo.save(plan);
       }
       else
       {
    	   return null;
       }
//		 return getContVideoList(userId);
	 }
	
	public boolean deletePlan(Plan help) {
		// TODO Auto-generated method stub
		planRepo.delete(help);
		
		return false;
	}
	public boolean deleteAllPlan() {
		// TODO Auto-generated method stub
		planRepo.deleteAll();
		
		return false;
	}


	public Plan getPlanByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Plan channel = mongoTemplate.findOne(query, Plan.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}
	 

}
