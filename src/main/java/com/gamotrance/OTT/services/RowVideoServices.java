package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.RowVideoRepo;
import com.gamotrance.OTT.Model.RowVideo;





@Repository
public class RowVideoServices {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	RowVideoRepo rowVideoRepo;
	public List<RowVideo> getAllRowVideo() {
		// TODO Auto-generated method stub
		List<RowVideo> list=mongoTemplate.findAll(RowVideo.class);
		return list;
	}

	public RowVideo getRowVideoById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        RowVideo singleVideo = mongoTemplate.findById(id, RowVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}


	public RowVideo getRowVideoByName(String name) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("title").is(name));
        RowVideo singleVideo = mongoTemplate.findOne(query, RowVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}
	
	public RowVideo addRowVideo(RowVideo rentMovie) {
		// TODO Auto-generated method stub
		rentMovie.setId(LocalDateTime.now().hashCode());
		return mongoTemplate.save(rentMovie);
	}

	public RowVideo updateRowVideo(RowVideo singleVideo) {
		// TODO Auto-generated method stub
		 	Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(singleVideo.getId()));
	        RowVideo user1 = mongoTemplate.findOne(query, RowVideo.class);
	     
	        if(user1!=null)
	            {
	        	return mongoTemplate.save(singleVideo);
	        }
	        return null;	
	}
	
	public boolean deleteRowVideo(int id) {
		// TODO Auto-generated method stub
		try {
			Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(id));
	        RowVideo user1 = mongoTemplate.findOne(query, RowVideo.class);
	        rowVideoRepo.delete(user1);
		return true;
		}catch(Exception e)
		{
			return false;
		}	
	}
	public boolean deleteAllRentMovie() {
		// TODO Auto-generated method stub
		try {
		rowVideoRepo.deleteAll();
		return true;
		}catch(Exception e)
		{
			return false;
		}	
	}
}
