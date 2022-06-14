package com.gamotrance.OTT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Doa.ChannelRepo;
import com.gamotrance.OTT.Model.Channel;

@Repository
public class ChannelServicesImplimentation implements ChannelServicesDoa{
	 @Autowired
	    ChannelRepo channelRepo;
	    @Autowired
	    MongoTemplate mongoTemplate;
		@Override
		public List<Channel> getAllChannel() {
			// TODO Auto-generated method stub
			return mongoTemplate.findAll(Channel.class);
		}
		@Override
		public Channel getChannelById(int id) {
			Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(id));
	        Channel channel = mongoTemplate.findOne(query, Channel.class);
	        System.out.println("userTest2 - " + channel);
	        
			return channel;
		}
		@Override
		public Channel getChannelByName(String name) {
			Query query = new Query();
	        query.addCriteria(Criteria.where("name").is(name));
	        Channel channel = mongoTemplate.findOne(query, Channel.class);
	        System.out.println("userTest2 - " + channel);
			return channel;
		}
		@Override
		public List<Channel> getChannelByGenre(String genreName) {
			// TODO Auto-generated method stub
			Query query = new Query();
	        query.addCriteria(Criteria.where("genreName").is(genreName));
	        List<Channel> channel = mongoTemplate.find(query, Channel.class);
	        System.out.println("userTest2 - " + channel);
			return channel;
		}
		@Override
		public Channel addChannel(Channel channel) {
			// TODO Auto-generated method stub
			return mongoTemplate.save(channel);
		}
		@Override
		public Channel updateChannel(Channel channel) {
			// TODO Auto-generated method stub
			return mongoTemplate.save(channel);
		}
		@Override
		public boolean deleteChannel(Channel channel) {
			// TODO Auto-generated method stub
			try
			{
				channelRepo.delete(channel);
				return true;
			}catch(Exception e)
			{
				return false;
			}
			
		}

}
