package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.HomePagerContain;


@Repository
public interface HomePagerContainRepo extends MongoRepository<HomePagerContain, String> {

}
