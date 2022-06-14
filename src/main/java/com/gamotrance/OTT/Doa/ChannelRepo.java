package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Channel;

@Repository
public interface ChannelRepo extends MongoRepository<Channel, String>{

}
