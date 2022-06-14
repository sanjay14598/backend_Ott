package com.gamotrance.OTT.services;

//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import com.gamotrance.OTT.Doa.WalletRepo;
//import com.gamotrance.OTT.Model.UserView;
//import com.gamotrance.OTT.Model.Wallet;
//
//@Repository
//public class WalletServices {
//	
//	@Autowired
//	WalletRepo WalletRepo;
//	
//    @Autowired
//    MongoTemplate mongoTemplate;
//    
//    
//    public Wallet addWallet(Wallet wallet)
//    {
//    	return mongoTemplate.save(wallet);
//    }
//    public Wallet updateWallet(Wallet wallet)
//    {
//    	Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(wallet.getId()));
//        Wallet userViewResponse = mongoTemplate.findOne(query, Wallet.class);
//        
//        if(userViewResponse!=null)
//		return mongoTemplate.save(userViewResponse);
//        
//        return null;
//    }
//    public void deleteWallet(Wallet wallet)
//    {
//    	 mongoTemplate.save(wallet);
//    }
//    public Wallet getWalletById(int id)
//    {
//    	Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//        Wallet userResponse = mongoTemplate.findOne(query, Wallet.class);
//        return userResponse;
//    }
//    
//    
//}
