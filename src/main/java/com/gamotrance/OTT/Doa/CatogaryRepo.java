package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Cataogry;
import com.gamotrance.OTT.Model.Role;

@Repository
public interface CatogaryRepo extends MongoRepository<Cataogry, String>{
	
	//Cataogry findByCataogry(String role);

}
