package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.UserNotif;
@Repository
public interface UserNotifRepo extends MongoRepository<UserNotif, String>{

}
