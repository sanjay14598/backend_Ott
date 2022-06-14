package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.DBanner;



@Repository
public interface DBannerRepo extends MongoRepository<DBanner, String>{

}
