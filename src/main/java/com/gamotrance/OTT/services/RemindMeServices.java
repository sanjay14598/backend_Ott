package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.RemindMeRepo;
import com.gamotrance.OTT.Model.RemindMe;
import com.gamotrance.OTT.Model.UserDocument;

import net.bytebuddy.asm.Advice.Local;

@Repository
public class RemindMeServices {

	@Autowired
	RemindMeRepo remindMeServices;
	@Autowired
	MongoTemplate mongoTemplate;

	public boolean addRemindMe(String userId, String vodId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("vodId").is(vodId).andOperator(Criteria.where("userId").is(userId)));
		RemindMe user1 = mongoTemplate.findOne(query, RemindMe.class);
		System.out.println("payment is on flow - " + user1);
		if (user1 != null) {
			return true;
		} else {
			String ids = userId + "" + vodId;
			mongoTemplate.save(new RemindMe(ids.hashCode(), vodId, userId));
			return false;
		}

	}

	public boolean updateRemindMe(String userId, String vodId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("vodId").is(vodId).andOperator(Criteria.where("userId").is(userId)));
		RemindMe user1 = mongoTemplate.findOne(query, RemindMe.class);
		// System.out.println("payment is on flow - " + user1);
		if (user1 != null) {
			remindMeServices.delete(user1);
			return true;
		} else {
			// mongoTemplate.save(new RemindMe(LocalDate.now().toString().hashCode(), vodId,
			// userId));
			return false;
		}

	}

	public List<String> getRemindList(String userId) {
		ArrayList<String> al = new ArrayList<String>();
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		List<RemindMe> user1 = mongoTemplate.find(query, RemindMe.class);
		System.out.println("payment is on flow - " + user1.size());
		for (RemindMe rm : user1) {
			al.add(rm.getVodId());
		}
		return al;
	}
}
