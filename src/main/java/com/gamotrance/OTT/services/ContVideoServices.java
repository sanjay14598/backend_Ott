package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.ContVideoRepo;
import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.ContVideo;


@Repository
public class ContVideoServices {
	
	@Autowired
	ContVideoRepo contVideoRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<ContVideo> addContVideo(ContVideo contVideo)
	 {
		mongoTemplate.save(contVideo);
		 return getContVideoList(contVideo.getUserId());
	 }
	
	public List<ContVideo> getContVideoList(String userId)
	 {
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<ContVideo> channel = mongoTemplate.find(query, ContVideo.class);
        System.out.println("userTest2 - " + channel);
		 return channel;
	 }
	public List<ContVideo> updateVideoByUser(String vodId,String userId)
	 {
		Query query = new Query();
        //query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("vodId").is(vodId))); 
        ContVideo channel = mongoTemplate.findOne(query, ContVideo.class);
        if(channel!=null)
        {
        	contVideoRepo.delete(channel);
        }
		 return getContVideoList(userId);
	 }
	public List<ContVideo> updateVideoByUser(ContVideo contVideo)
	 {
		 Query query = new Query();
	        query.addCriteria(Criteria.where("userId").is(contVideo.getUserId()).andOperator(Criteria.where("vodId").is(contVideo.getVodId()))); 
	        ContVideo channel = mongoTemplate.findOne(query, ContVideo.class);
       System.out.println("the elec");
       if(channel!=null)
       {
    	   System.out.println("the mongodb: "+channel.getUserId()+" "+channel.getVodId()+" "+channel.getLastTime());
    	   channel.setLastTime(contVideo.getLastTime());
    	   mongoTemplate.save(channel);
    	   System.out.println("the elec");
       }else
       {
    	   System.out.println("the mongodb: "+contVideo.getUserId()+" "+contVideo.getVodId()+" "+contVideo.getLastTime());
    	   contVideo.setId((contVideo.getVodId()+""+contVideo.getUserId()).hashCode());
    	   mongoTemplate.save(contVideo);
    	   System.out.println("the elec");
       }
       Query query1 = new Query();
       query1.addCriteria(Criteria.where("userId").is(contVideo.getUserId()));
       List<ContVideo> channel1 = mongoTemplate.find(query1, ContVideo.class);
       System.out.println("the elec"+channel1.size());
		 return channel1;
	 }

	public String deleteAll()
	 {
		contVideoRepo.deleteAll();
		 return "Deleted";
	 }

}
