package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.RentAccess;

@Repository
public interface RentAccessRepo extends MongoRepository<RentAccess, String>{

}
