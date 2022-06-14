package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;
import com.gamotrance.OTT.Doa.HelpRepo;
import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.Help;


@Repository
public class HelpServices implements HelpServicesDao{
	
	@Autowired
	HelpRepo helprepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Help addHelp(Help help) {
		// TODO Auto-generated method stub
help.setId((help.getUserId()+""+LocalDate.now().toString()).hashCode());
		return helprepo.save(help);
	}

	@Override
	public List<Help> getHelpALL() {
		// TODO Auto-generated method stub
		
		return helprepo.findAll();
	}

	@Override
	public List<Help> getByListHelp(int userId) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<Help> channel = mongoTemplate.find(query, Help.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

	@Override
	public List<Help> getHelpById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<Help> channel = mongoTemplate.find(query, Help.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}

	@Override
	public boolean deleteHelp(Help help) {
		// TODO Auto-generated method stub
		helprepo.delete(help);
		
		return false;
	}

	@Override
	public Help getHelpByIds(int id) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Help channel = mongoTemplate.findOne(query, Help.class);
        System.out.println("userTest2 - " + channel);
		return channel;
	}
}
