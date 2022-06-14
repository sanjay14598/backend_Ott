package com.gamotrance.OTT.Doa;

import com.gamotrance.OTT.Model.UserFcmToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFcmTokenRepo extends MongoRepository<UserFcmToken, Long> {
}
