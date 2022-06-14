package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.WatchList;
@Repository
public interface WatchListRepo extends MongoRepository<WatchList, String>{

}
