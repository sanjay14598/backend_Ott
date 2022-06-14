package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserView;
@Repository
public interface UserViewRepo extends MongoRepository<UserView, String>{


}
