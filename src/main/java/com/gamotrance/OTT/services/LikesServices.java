package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.LikesRepo;
import com.gamotrance.OTT.Model.Likes;
import com.gamotrance.OTT.Model.SingleVideo;
@Repository
public class LikesServices implements LikesServicesDoa{
	
	@Autowired
	LikesRepo likesRepo;
	
	@Autowired
 	MongoTemplate mongoTemplate;
	

	@Override
	public Likes addLike(Likes likes) {
		// TODO Auto-generated method stub
		return likesRepo.save(likes);
	}

	@Override
	public Likes updateLike(Likes likes) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(likes));
        Likes like = mongoTemplate.findOne(query, Likes.class);
     
		return like;
	}

	@Override
	public void deleteLike(Likes likes) {
		// TODO Auto-generated method stub
		likesRepo.delete(likes);
	}


	@Override
	public List<Likes> getLikesByVideo(String videoId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("videoId").is(videoId));
        List<Likes> singleVideo = mongoTemplate.find(query, Likes.class);
     
		return singleVideo;
	}

	@Override
	public List<Likes> getLikesByUser(String userId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<Likes> singleVideo = mongoTemplate.find(query, Likes.class);
     
		return singleVideo;
	}

	@Override
	public Likes getLikesExistance(String userId, String videoId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("videoId").is(videoId));
        Likes singleVideo = mongoTemplate.findOne(query, Likes.class);
     
		return singleVideo;
	}

	@Override
	public Likes getbyIds(String userId, String vodId) {
		// TODO Auto-generated method stub
		return null;
	}
}
