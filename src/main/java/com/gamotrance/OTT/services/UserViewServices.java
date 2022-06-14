package com.gamotrance.OTT.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.UserViewRepo;
import com.gamotrance.OTT.Model.RemindMe;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.UserView;


@Repository
public class UserViewServices implements UserViewDoa {
	@Autowired
    UserViewRepo userViewRepo;
    @Autowired
    MongoTemplate mongoTemplate;
    
    
    @Override
	public UserView addUserView(UserView userView) {
		//userView.setId(flag);
		// TODO Auto-generated method stub
		userView.setStartTimeStamp(LocalDate.now());
		return mongoTemplate.save(userView);
	}
    
	/*
	 * public boolean addViewOnVideoOneTimeOnly(String userId, String vodId) { Query
	 * query = new Query();
	 * query.addCriteria(Criteria.where("vodId").is(vodId).andOperator(Criteria.
	 * where("userId").is(userId))); UserView user1 = mongoTemplate.findOne(query,
	 * UserView.class); if (user1 != null) { return true; } else { String ids =
	 * userId + "" + vodId; mongoTemplate.save(new UserView(ids.hashCode(), vodId,
	 * userId)); return false; }
	 * 
	 * }
	 */

	@Override
	public UserView updateUserView(UserView userView) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userView.getVideoId()));
        UserView userViewResponse = mongoTemplate.findOne(query, UserView.class);
        userViewResponse.setEndTimeStamp(LocalDate.now());
        
        if(userViewResponse!=null)
		return mongoTemplate.save(userViewResponse);
        
        return null;
	}

	@Override
	public UserView getUserViewById(Integer id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        UserView userViewResponse = mongoTemplate.findOne(query, UserView.class);
		return userViewResponse;
	}

}
