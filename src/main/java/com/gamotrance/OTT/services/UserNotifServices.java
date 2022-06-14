package com.gamotrance.OTT.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.UserNotifRepo;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserNotif;


@Repository
public class UserNotifServices {
	@Autowired
    UserNotifRepo userNotifRepo;
    @Autowired
    MongoTemplate mongoTemplate;
    
    public UserNotif updateToken(UserNotif user)
    {
    	Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        UserNotif user1 = mongoTemplate.findOne(query, UserNotif.class);
        
        if(user1!=null)
            {
        	System.out.println("update Token - " + user1.getToken());
    return mongoTemplate.save(user);
        }else {
        	return mongoTemplate.save(user);
        }
        
    }
    public void deleteToken(int id)
    {
    	Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        UserNotif user1 = mongoTemplate.findOne(query, UserNotif.class);
        userNotifRepo.delete(user1);
    }

}
