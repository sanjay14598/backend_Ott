package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.AppUpdateRepo;
import com.gamotrance.OTT.Doa.ContVideoRepo;
import com.gamotrance.OTT.Model.AppUpdate;
import com.gamotrance.OTT.Model.ContVideo;

@Repository
public class AppUpdateServices {
	@Autowired
	AppUpdateRepo contVideoRepo;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public AppUpdate addAppUpdate(AppUpdate contVideo)
	 {
		return mongoTemplate.save(contVideo);
	 }
	
	public AppUpdate getAppUpdate()
	 {
		return mongoTemplate.findAll(AppUpdate.class).get(0);
	 }
	
	public String deleteAll()
	 {
		contVideoRepo.deleteAll();
		 return "Deleted";
	 }

}