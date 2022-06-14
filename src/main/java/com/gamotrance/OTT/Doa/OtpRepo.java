package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Otp;

@Repository
public interface OtpRepo extends MongoRepository<Otp, String>{

}
