package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.Wallet;

@Repository
public interface WalletRepo extends MongoRepository<Wallet, String>{

}
