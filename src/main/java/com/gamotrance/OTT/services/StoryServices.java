package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

//import com.gamotrance.OTT.Doa.SingleVideoRepo;
import com.gamotrance.OTT.Doa.StoryDoa;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.Story;

@Repository
public class StoryServices {

	@Autowired
	StoryDoa singleVideoRepo;
	
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
		
		public List<Story> getAllStory() {
			// TODO Auto-generated method stub
			return mongoTemplate.findAll(Story.class);
		}
		
//		public boolean syncAll(List<SingleVideo> user) {
//			// TODO Auto-generated method stub
//			singleVideoRepo.saveAll(user);
//			return true;
//		}

	
		public Story getStoryById(int id) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(id));
	        Story singleVideo = mongoTemplate.findById(id, Story.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}

	
		public Story getStoryByName(String name) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("title").is(name));
	        Story singleVideo = mongoTemplate.findOne(query, Story.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}

//		@Override
//		public List<SingleVideo> getSingleVideoByGenre(String genreName) {
//			Query query = new Query();
//	        query.addCriteria(Criteria.where("genres.genreName").is(genreName));
//	        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
//	        System.out.println("userTest2 - " + singleVideo);
//			return singleVideo;
//		}

		
		public Story addStory(Story story) {
			// TODO Auto-generated method stub
			return mongoTemplate.save(story);
		}

		public Story updateStory(Story singleVideo) {
			// TODO Auto-generated method stub
			 	Query query = new Query();
		        query.addCriteria(Criteria.where("id").is(singleVideo.getId()));
		        Story user1 = mongoTemplate.findOne(query, Story.class);
		     
		        if(user1!=null)
		            {
		        	return mongoTemplate.save(singleVideo);
		        }
		        return null;	
		}

		
		public boolean deleteStory(Story singleVideo) {
			// TODO Auto-generated method stub
			try {
			singleVideoRepo.delete(singleVideo);
			return true;
			}catch(Exception e)
			{
				return false;
			}	
		}
}
