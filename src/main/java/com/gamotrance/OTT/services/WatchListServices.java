package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.threeten.bp.LocalDate;

import com.gamotrance.OTT.Doa.WatchListRepo;
import com.gamotrance.OTT.Model.*;

@Repository
public class WatchListServices {

	@Autowired
	WatchListRepo watchListRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public WatchList addContVideo(WatchList contVideo)
	 {
		contVideo.setId(LocalDateTime.now().hashCode());
		 return mongoTemplate.save(contVideo);
	 }
	public WatchList getVideoByUser(String vodId,String userId)
	 {
		Query query = new Query();
       query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("vodId").is(vodId)));
       WatchList channel = mongoTemplate.findOne(query, WatchList.class);
       if(channel!=null)
       {
       	return channel;
       }
		 return null;
	 }
	
	
	public List<WatchList> getWatchList(int userId)
	 {
		Query query = new Query();
      query.addCriteria(Criteria.where("userId").is(userId));
      List<WatchList> channel = mongoTemplate.find(query, WatchList.class);
     
		 return channel;
	 }
	
	
	public boolean deleteContVideo(WatchList contVideo)
	 {
		
		Query query = new Query();
	       query.addCriteria(Criteria.where("userId").is(contVideo.getUserId()).andOperator(Criteria.where("vodId").is(contVideo.getVodId())));
	       WatchList channel = mongoTemplate.findOne(query, WatchList.class);
	       if(channel!=null)
	       {
	    	   mongoTemplate.remove(channel);
	       }
			 //return null;
		
		 return true;
	 }
}
