package com.gamotrance.OTT.Doa;

import com.gamotrance.OTT.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepo extends MongoRepository<User, String>{
	User findByEmail(String email);
	User findByPhone(String phone);

}
