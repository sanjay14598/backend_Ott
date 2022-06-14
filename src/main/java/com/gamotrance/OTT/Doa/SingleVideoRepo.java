package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.SingleVideo;
@Repository
public interface SingleVideoRepo extends MongoRepository<SingleVideo, String>{

}

