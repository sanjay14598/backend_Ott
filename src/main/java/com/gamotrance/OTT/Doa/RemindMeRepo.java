package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.RemindMe;


@Repository
public interface RemindMeRepo extends MongoRepository<RemindMe, String>{

}
