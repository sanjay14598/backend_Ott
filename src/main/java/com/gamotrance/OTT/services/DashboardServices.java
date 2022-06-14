package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.UserRepo;
import com.gamotrance.OTT.Model.ChatXY;
import com.gamotrance.OTT.Model.DashValue;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.User;


@Repository
public class DashboardServices {
	@Autowired
	UserRepo userRepo;
	@Autowired
	SingleVideoDoa singleVideoDoa;
	@Autowired
	MongoTemplate mongoTemplate;
	public List<List<Map<Object, Object>>> getChartList()
	{
		 Map<Object,Object> map = null;
		List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
		List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
		List<User> users=userRepo.findAll();
		List<ChatXY> chlist=new ArrayList<>();
		
		for(Long i=(long)0;i<7;i++)
		{
			int count=0;
			for(User u:users)
			{
				if(LocalDate.now().minusDays(i).isEqual(u.getCreatedAt()))
				{
					count++;
				}
				
			}
			chlist.add(new ChatXY(LocalDate.now().minusDays(i).toString(), count));
		}
		for(ChatXY ch:chlist)
		{
			map = new HashMap<Object,Object>();
			map.put("x", ch.getDays());
			map.put("y", ch.getUserCount());
			dataPoints1.add(map);
		}
		list.add(dataPoints1);
		return list;
	}
	public int getUserCount()
	{
		return userRepo.findAll().size();
	}
	public int getMovieCount()
	{
		
		Query query = new Query();
        query.addCriteria(Criteria.where("videoType").is("MOVIE"));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
		
		return singleVideo.size();
	}
	public int getsortMovieCount()
	{
		Query query = new Query();
        query.addCriteria(Criteria.where("videoType").is("SORTMOVIE"));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
		
		return singleVideo.size();
	}
	public int getWebCount()
	{
		Query query = new Query();
        query.addCriteria(Criteria.where("videoType").is("WEBSERIES"));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
		
		return singleVideo.size();
	}
	public int getContentCount()
	{	
		return singleVideoDoa.getAllSingleVideo().size();
	}
	public int getSubCount()
	{
//		Query query = new Query();
//        query.addCriteria(Criteria.where("userType").is("ONEYEAR").orOperator(Criteria.where("userType").is("SIXMONTHS").orOperator(Criteria.where("userType").is("THREEMONTHS")).orOperator(Criteria.where("userType").is("ONEMONTH"))));
//        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
		
		return 0;
	}
	public long getTotalAmount()
	{
//		Query query = new Query();
//        query.addCriteria(Criteria.where("userType").is("ONEYEAR").orOperator(Criteria.where("userType").is("SIXMONTHS").orOperator(Criteria.where("userType").is("THREEMONTHS")).orOperator(Criteria.where("userType").is("ONEMONTH"))));
//        List<User> singleVideo = mongoTemplate.find(query, User.class);
//		long amount=0;
//        for(User s:singleVideo)
//		{
//			amount =amount+Long.valueOf(s.getPayments().getAmount());
//		}
		return 0;
	}
	public int getNonSubUser()
	{
		Query query = new Query();
        query.addCriteria(Criteria.where("userType").is("BASIC"));
        List<User> singleVideo = mongoTemplate.find(query, User.class);
		
		return singleVideo.size();
	}
	public DashValue getValue()
	{
		return new DashValue(getContentCount(), getMovieCount(), getsortMovieCount(), getWebCount(), getUserCount(), getSubCount(), (int)getTotalAmount(), getNonSubUser(), getContentCount());
	}
	
}
