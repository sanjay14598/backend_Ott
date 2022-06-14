package com.gamotrance.OTT.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.VideoCatListRepo;
import com.gamotrance.OTT.Model.UserView;
import com.gamotrance.OTT.Model.VideoCatList;

@Repository
public class VideoCatListServices {
	@Autowired
	VideoCatListRepo videoCatListRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public VideoCatList addDash(VideoCatList userView) {
		//userView.setId(flag);
		// TODO Auto-generated method stub
		//userView.setStartTimeStamp(LocalDate.now());
		System.out.println("the video String"+userView.toString());
		return mongoTemplate.save(userView);
	}

	
	public VideoCatList updateVideoCatList(VideoCatList userView) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userView.getId()));
        VideoCatList userViewResponse = mongoTemplate.findOne(query, VideoCatList.class);
        //userViewResponse.setEndTimeStamp(LocalDate.now());
        
        if(userViewResponse!=null)
		return mongoTemplate.save(userViewResponse);
        
        return null;
	}

	public boolean getVideoCatListByIdExist(Integer id) {
		// TODO Auto-generated method stub
		try {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        VideoCatList userViewResponse = mongoTemplate.findOne(query, VideoCatList.class);
        if(userViewResponse!=null)
        {
        	return true;
        }
        else
        {
        	return false;
        }
		}catch(Exception e)
        {
        	return false;
        }
		//return userViewResponse;
	}
	
	public VideoCatList getVideoCatListById(Integer id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        VideoCatList userViewResponse = mongoTemplate.findOne(query, VideoCatList.class);
		return userViewResponse;
	}

}
