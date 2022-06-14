package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.PaymentMethodRepo;
import com.gamotrance.OTT.Model.PaymentMethod;
import com.gamotrance.OTT.Model.Plan;
@Repository
public class PaymentMethodSeervices {
	
	@Autowired
	PaymentMethodRepo paymentMethodRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public PaymentMethod addPaymentMethod(PaymentMethod help) {
		// TODO Auto-generated method stub
help.setId((help.getId()+""+LocalDateTime.now().toString()).hashCode());
		return paymentMethodRepo.save(help);
	}


	public List<PaymentMethod> getPaymentMethodAll() {
		// TODO Auto-generated method stub
		
		return paymentMethodRepo.findAll();
	}


	public List<PaymentMethod> getByListPaymentMethod(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<PaymentMethod> channel = mongoTemplate.find(query, PaymentMethod.class);
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
	public PaymentMethod updatePaymentMethod(PaymentMethod plan)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("id").is(plan.getId()));
       PaymentMethod channel = mongoTemplate.findOne(query, PaymentMethod.class);
       if(channel!=null)
       {
       	return paymentMethodRepo.save(plan);
       }
       else
       {
    	   return null;
       }
//		 return getContVideoList(userId);
	 }
	
	public boolean deletePaymentMethod(PaymentMethod help) {
		// TODO Auto-generated method stub
		paymentMethodRepo.delete(help);
		
		return false;
	}
	public boolean deleteAllPaymentMethod() {
		// TODO Auto-generated method stub
		paymentMethodRepo.deleteAll();
		
		return false;
	}


	public PaymentMethod getPaymentMethodByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        PaymentMethod channel = mongoTemplate.findOne(query, PaymentMethod.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}
	 
}
