package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;


import com.gamotrance.OTT.Doa.CatogaryRepo;
import com.gamotrance.OTT.Model.Cataogry;
import com.gamotrance.OTT.Model.Channel;


@Repository
public class CatogarryServices {

	@Autowired
	CatogaryRepo contVideoRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public Cataogry addCataogry(Cataogry contVideo)
	 {
		return mongoTemplate.save(contVideo);
	 }
	public List<Cataogry> getAllCataogry() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Cataogry.class);
	}
	
	public String deleteAll()
	 {
		contVideoRepo.deleteAll();
		 return "Deleted";
	 }
	
	public Cataogry updateCataogry(Cataogry channel) {
		// TODO Auto-generated method stub
		return mongoTemplate.save(channel);
	}
	
	public boolean deleteCataogry(Cataogry channel) {
		// TODO Auto-generated method stub
		try
		{
			contVideoRepo.delete(channel);
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
	public boolean deleteAllCataogry() {
		// TODO Auto-generated method stub
		try
		{
			contVideoRepo.deleteAll();
			return true;
		}catch(Exception e)
		{
			return false;
		}
		
	}
}
