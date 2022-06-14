package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Story;
//import com.gamotrance.OTT.Model.Transcation;
@Repository
public interface StoryDoa extends MongoRepository<Story, String>{

}
