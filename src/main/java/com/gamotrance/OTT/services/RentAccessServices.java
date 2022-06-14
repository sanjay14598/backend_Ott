package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.OttUtil;
import com.gamotrance.OTT.Doa.RentAccessRepo;
import com.gamotrance.OTT.Doa.RentVideoRepo;
import com.gamotrance.OTT.Model.Access;
import com.gamotrance.OTT.Model.Payment;
import com.gamotrance.OTT.Model.RentAccess;
import com.gamotrance.OTT.Model.RentMovie;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserType;

@Repository
public class RentAccessServices {
	@Autowired
	RentAccessRepo rentVideoRepo;
	
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
		
		public List<RentAccess> getAllRentAccess() {
			// TODO Auto-generated method stub
			return mongoTemplate.findAll(RentAccess.class);
		}
		
//		public boolean syncAll(List<SingleVideo> user) {
//			// TODO Auto-generated method stub
//			singleVideoRepo.saveAll(user);
//			return true;
//		}

	
		public RentAccess getRentAccessById(int id) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(id));
	        RentAccess singleVideo = mongoTemplate.findById(id, RentAccess.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}
		public List<RentAccess> getRentAccessListById(String id) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("userId").is(id));
	        List<RentAccess> singleVideo = mongoTemplate.find(query, RentAccess.class);
	        System.out.println("userTest2 - " + singleVideo);
			return singleVideo;
		}

	
		public boolean getRentAccessByUserId(String userId,String vdoId) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("vdoId").is(vdoId)));
	       RentAccess rs= mongoTemplate.findOne(query, RentAccess.class);
	       System.out.println(rs.getUserId()+" "+rs.getVdoId());
	        if(rs!=null)
	        {
	        	return true;
	        }else
	        {
	        	return false;
	        }
	        
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		  //String date = "16/08/2016";

		  //convert String to LocalDate
		  
		public void updateAccess() {
			List<RentAccess> allUser=mongoTemplate.findAll(RentAccess.class);
		//	List<Access> rentAccess=new ArrayList<Access>();
			for(RentAccess user:allUser)
			{
				//rentAccess=new ArrayList<Access>();
				
					
						if(LocalDate.now().equals(LocalDate.parse(user.getExpDate(), formatter)))
						{
							rentVideoRepo.delete(user);
						}
					
					//updateRentAccess(new RentAccess(user.getId(),user.getVdoId(),rentAccess));
			
				
			}
	        
		}
		
		public RentAccess addRentAccess(RentAccess rentMovie) {
			// TODO Auto-generated method stub
			System.out.println("rent Access Added"+rentMovie.getUserId()+"" +rentMovie.getVdoId());
			return mongoTemplate.save(rentMovie);
		}

		public RentAccess updateRentAccess(RentAccess singleVideo) {
			// TODO Auto-generated method stub
			 	Query query = new Query();
		        query.addCriteria(Criteria.where("id").is(singleVideo.getId()));
		        RentAccess user1 = mongoTemplate.findOne(query, RentAccess.class);
		     
		        if(user1!=null)
		            {
		        	return mongoTemplate.save(singleVideo);
		        }
		        return null;	
		}

		
		public boolean deleteRentAccess(RentAccess singleVideo) {
			// TODO Auto-generated method stub
			try {
			rentVideoRepo.delete(singleVideo);
			return true;
			}catch(Exception e)
			{
				return false;
			}	
		}
		public boolean deleteAllRentAccess() {
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
