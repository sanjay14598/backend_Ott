package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.RowVideo;






@Repository
public interface RowVideoRepo extends MongoRepository<RowVideo, String>{

}
