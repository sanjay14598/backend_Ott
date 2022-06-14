package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.DBannerRepo;
import com.gamotrance.OTT.Model.DBanner;



@Repository
public class DBannerServices {
	@Autowired
	DBannerRepo helprepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	

	public DBanner addDBanner(DBanner help) {
		// TODO Auto-generated method stub
help.setId((help.getBannerName()+""+LocalDateTime.now().toString()).hashCode());
		return helprepo.save(help);
	}


	public List<DBanner> getDBannerALL() {
		// TODO Auto-generated method stub
		
		return helprepo.findAll();
	}


	public List<DBanner> getByListDBanner(int userId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<DBanner> channel = mongoTemplate.find(query, DBanner.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

	
	public List<DBanner> getDBannerById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<DBanner> channel = mongoTemplate.find(query, DBanner.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

	
	public boolean deleteDBanner(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DBanner channel = mongoTemplate.findOne(query, DBanner.class);
		helprepo.delete(channel);
		
		return false;
	}

	public boolean deleteAllDBanner() {
		// TODO Auto-generated method stub
		helprepo.deleteAll();
		
		return false;
	}

	
	public DBanner getDBannerByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DBanner channel = mongoTemplate.findOne(query, DBanner.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}
	public DBanner updateDBanner(DBanner plan)
	 {
		Query query = new Query();
      query.addCriteria(Criteria.where("id").is(plan.getId()));
      DBanner channel = mongoTemplate.findOne(query, DBanner.class);
      if(channel!=null)
      {
      	return helprepo.save(plan);
      }
      else
      {
   	   return null;
      }
//		 return getContVideoList(userId);
	 }
}
