package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.ChannelRepo;
import com.gamotrance.OTT.Doa.HomePagerContainRepo;
import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.HomePagerContain;
import com.gamotrance.OTT.Model.TransctiionField;

@Repository
public class HomePagerContainerServices {
	@Autowired
    HomePagerContainRepo homePagerContainRepo;
    @Autowired
    MongoTemplate mongoTemplate;

//    Channel getChannelByName(String name);
	 public List<HomePagerContain> getHomePagerContainer()
	 {
		 return homePagerContainRepo.findAll();
	 }
	public String addToHomePagerContain(HomePagerContain homePagerContain)
	 {
		 mongoTemplate.save(homePagerContain);
		 return "Added";
	 }
	public String deleteAll()
	 {
		 homePagerContainRepo.deleteAll();
		 return "Deleted";
	 }
	public String verifyHomePager()
	{
		List<HomePagerContain> list=homePagerContainRepo.findAll();
		
		for(HomePagerContain hm:list)
		{
			LocalDate ld=LocalDate.parse(hm.getSubDate());
			System.out.println(ld.toString());
			System.out.println(ld.plusDays(Long.parseLong(hm.getExpDate())).toString());
			if(ld.equals(ld.plusDays(Long.parseLong(hm.getExpDate()))))
			{
				mongoTemplate.remove(hm);
			}
		}
		
		return null;
	}
	public String deleteSingleDash(int id)
	 {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        HomePagerContain channel = mongoTemplate.findOne(query, HomePagerContain.class);
        if(channel!=null)
        {
        	mongoTemplate.remove(channel);
        	return "Deleted";
        }else
        {
        	return "unAttempt";
        }
		// homePagerContainRepo.deleteAll();
		 
	 }
    
}
