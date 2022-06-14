package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.FavouriteRepo;
import com.gamotrance.OTT.Model.Favourite;



@Repository
public class FavouriteServices {
	
	@Autowired
	FavouriteRepo watchListRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Favourite addFavourite(Favourite contVideo)
	 {
		contVideo.setId(LocalDateTime.now().hashCode());
		 return mongoTemplate.save(contVideo);
	 }
	public Favourite getFavouriteByUser(String vodId,String userId)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("vodId").is(vodId)));
       Favourite channel = mongoTemplate.findOne(query, Favourite.class);
       if(channel!=null)
       {
       	return channel;
       }
		 return null;
	 }
	
	
	public List<Favourite> getFavouriteList(int userId)
	 {
		Query query = new Query();
      query.addCriteria(Criteria.where("userId").is(userId));
      List<Favourite> channel = mongoTemplate.find(query, Favourite.class);
     
		 return channel;
	 }
	
	
	public boolean deleteFavourite(Favourite contVideo)
	 {
		
		Query query = new Query();
	       query.addCriteria(Criteria.where("userId").is(contVideo.getUserId()).andOperator(Criteria.where("vodId").is(contVideo.getVodId())));
	       Favourite channel = mongoTemplate.findOne(query, Favourite.class);
	       if(channel!=null)
	       {
	    	   mongoTemplate.remove(channel);
	       }
			 //return null;
		
		 return true;
	 }

}
