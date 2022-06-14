package com.gamotrance.OTT.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.RentVideoRepo;
import com.gamotrance.OTT.Model.RentMovie;
import com.gamotrance.OTT.Model.Story;


@Repository
public class RentMovieServices {
	
	
	@Autowired
	RentVideoRepo rentVideoRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
		
		public List<RentMovie> getAllRentMovie() {
			// TODO Auto-generated method stub
			List<RentMovie> list=mongoTemplate.findAll(RentMovie.class);
			return list;
		}
	
		public RentMovie getRentMovieById(int id) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(id));
	        RentMovie singleVideo = mongoTemplate.findById(id, RentMovie.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}

	
		public RentMovie getRentMovieByName(String name) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("title").is(name));
	        RentMovie singleVideo = mongoTemplate.findOne(query, RentMovie.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}
		
		public RentMovie addRentMovie(RentMovie rentMovie) {
			// TODO Auto-generated method stub
			rentMovie.setId(LocalDateTime.now().hashCode());
			return mongoTemplate.save(rentMovie);
		}

		public RentMovie updateRentMovie(RentMovie singleVideo) {
			// TODO Auto-generated method stub
			 	Query query = new Query();
		        query.addCriteria(Criteria.where("id").is(singleVideo.getId()));
		        RentMovie user1 = mongoTemplate.findOne(query, RentMovie.class);
		     
		        if(user1!=null)
		            {
		        	return mongoTemplate.save(singleVideo);
		        }
		        return null;	
		}
		
		public boolean deleteRentMovie(RentMovie singleVideo) {
			// TODO Auto-generated method stub
			try {
			rentVideoRepo.delete(singleVideo);
			return true;
			}catch(Exception e)
			{
				return false;
			}	
		}
		public boolean deleteAllRentMovie() {
			// TODO Auto-generated method stub
			try {
			rentVideoRepo.deleteAll();
			return true;
			}catch(Exception e)
			{
				return false;
			}	
		}
		
}