package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Favourite;

@Repository
public interface FavouriteRepo extends MongoRepository<Favourite, String>{

}
