package com.gamotrance.OTT.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.CountryVideoDecodRepo;
import com.gamotrance.OTT.Model.CountryVideoDecoded;
import com.gamotrance.OTT.Model.HomePagerContain;
import com.gamotrance.OTT.Model.Likes;


@Repository
public class CountryVideoDecoedServices {
	@Autowired
    CountryVideoDecodRepo countryVideoDecodRepo;
    @Autowired
    MongoTemplate mongoTemplate;
    public List<Integer> getHomePagerContainer(String cont)
	 {
    	List<Integer> cvd=new ArrayList();
    	//cvd =countryVideoDecodRepo.findAll().stream().filter(c->c.getCountrylist().contains(cont) > true).collect(collector.to);
    	for(CountryVideoDecoded cd:countryVideoDecodRepo.findAll())
    	{
    		if(cd.getCountrylist().contains(cont))
    		{
    			cvd.add(cd.getId());
    		}
    	}
    	
		return cvd;
	 }
    
    public String addCountryVideoDecoded(CountryVideoDecoded countryVideoDecoded)
	 {
		 mongoTemplate.save(countryVideoDecoded);
		 return "Added";
	 }
	public String deleteAll()
	 {
		 countryVideoDecodRepo.deleteAll();
		 return "Deleted";
	 }
    
}
