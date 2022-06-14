package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.DisLike;

@Repository
public interface DisLikeRepo extends MongoRepository<DisLike, String>{

}
