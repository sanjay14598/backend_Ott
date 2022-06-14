package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Transcation;
@Repository
public interface TransctionRepo extends MongoRepository<Transcation, String>{

}
