package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.DisLikeRepo;
import com.gamotrance.OTT.Model.DisLike;
import com.gamotrance.OTT.Model.Likes;

@Repository
public class DisLikeSevices {
	@Autowired
	DisLikeRepo disLikeRepo;
	@Autowired
	MongoTemplate mongoTemplate;

	public DisLike addDisLike(DisLike likes) {
		// TODO Auto-generated method stub
		return disLikeRepo.save(likes);
	}

	
	public DisLike updateDisLike(DisLike likes) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(likes));
        DisLike like = mongoTemplate.findOne(query, DisLike.class);
     
		return like;
	}

	
	public void deleteDisLike(DisLike likes) {
		// TODO Auto-generated method stub
		disLikeRepo.delete(likes);
	}



	public List<DisLike> getDisLikeByVideo(String videoId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("videoId").is(videoId));
        List<DisLike> singleVideo = mongoTemplate.find(query, DisLike.class);
     
		return singleVideo;
	}

	
	public List<DisLike> getDisLikeByUser(String userId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<DisLike> singleVideo = mongoTemplate.find(query, DisLike.class);
     
		return singleVideo;
	}

	
	public DisLike getDisLikeExistance(String userId, String videoId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("videoId").is(videoId));
        DisLike singleVideo = mongoTemplate.findOne(query, DisLike.class);
     
		return singleVideo;
	}


	public DisLike getbyIds(String userId, String vodId) {
		// TODO Auto-generated method stub
		return null;
	}

}
