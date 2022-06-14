package com.gamotrance.OTT.controller;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gamotrance.OTT.services.WalletServices;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@RestController
//@RequestMapping(value="/Wallet")
//public class WalletController {
////	
////	 
//	private final Logger LOG = LoggerFactory.getLogger(getClass());
////	private final CoinTransactionServices coinTranscation;
//	    private final WalletServices salesRepository;
////	    private final TransctionSErvices transctionSErvices;
////	    private final UserServicesImplimentation usml;
//    private final UserWalletServices userWalletServices;
////	    private final UserTranscationServices userTranscationServices;
////	    private final SingleVideoServicesImplimentation singleVideoServicesImplimentation;
////	    private final ProfileContentServices profileContentServices;
////	    public WalletController(ProfileContentServices profileContentServices,SingleVideoServicesImplimentation singleVideoServicesImplimentation,CoinTransactionServices coinTranscation,UserTranscationServices userTranscationServices,UserWalletServices userWalletServices,WalletServices salesRepository, TransctionSErvices transctionSErvices,UserServicesImplimentation usml,CreatorServices creatorServices) {
////	    	this.userTranscationServices=userTranscationServices;
////	    	this.userWalletServices=userWalletServices;
////			this.salesRepository = salesRepository;
////			this.transctionSErvices = transctionSErvices;
////			this.usml=usml;
////			this.coinTranscation=coinTranscation;
////			this.profileContentServices=profileContentServices;
////			this.singleVideoServicesImplimentation=singleVideoServicesImplimentation;
////		}   
////	    
////	    
////	    @RequestMapping(value = "/addTransction", method = RequestMethod.POST)
////	    public User addTransction(@RequestBody Transcation transcation) {
////	        LOG.info("Transction user."+transcation.getId());
////	       User user= usml.getSubUser(transcation.getUserId());
////	     //  LOG.info("Transction user."+transcation.toString()+"user "+user.toString());
////	      // UserType ut=UserType.ONEMONTH;
////	       LOG.info("Transction user."+transcation.getTXNAMOUNT());
////	       //System.out.println(t);
////	       //LOG.info("Transction user."+transctionSErvices.addTranction(transcation).toString());
////	       List<Integer> trs;
////	       if(user.getUserPermList()!=null)
////	       {
////	    	   trs=user.getUserPermList();
////	       }else
////	       {
////	    	   trs=new ArrayList<Integer>();
////	       }
////	
////	       if((long)transcation.getTXNAMOUNT()!=0)
////	       {
////	    	   trs.add(transcation.getBrodcastId());
////	       }
////	       
////	       user.setUserPermList(trs);
////	   transctionSErvices.addTranction(transcation);
////	  // creatorServices.getSubCreator(id)
////	   Long d=(long)0;
////	   Long c=(long)0;
////	   Wallet wl=salesRepository.getWalletById(transcation.getCreatorId());
////	   
////	   d=(long)wl.getTotalAmount();
////	   List<Payment> pt=wl.getPayments();
////	   
////	   wl.setSubscriptionType(PaymentType.CREDIT.value);
////	   Payment pm;
////	   if(pt==null)
////	   {
////		   pt=new ArrayList<Payment>();
////		   pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),OttUtil.amountToCoins(transcation.getTXNAMOUNT()),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		  pt.add(pm);
////	   }else
////	   {
////		   pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),OttUtil.amountToCoins(transcation.getTXNAMOUNT()),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////			  pt.add(pm);  
////	   }
////	   
////	   wl.setPayments(pt);
////	   d=d+transcation.getTXNAMOUNT();
////	   c=c+OttUtil.amountToCoins(transcation.getTXNAMOUNT());
////	   wl.setTotalAmount(d);
////	   wl.setTotalcoins(c);
////	   salesRepository.updateWallet(wl);
////	        return usml.updateUserWithId(user);
////	    }
////	    @RequestMapping(value = "/deleteTransction", method = RequestMethod.DELETE)
////	    public String deleteTransction(@RequestBody Transcation transcation) {
////	        LOG.info( "user."+transcation.getId());
////	        transctionSErvices.deleteTranction(transcation);
////	        return null;
////	    }
////	    @RequestMapping(value = "/getTransctionById/{id}/", method = RequestMethod.GET)
////	    public Transcation getTransction(@PathVariable String id) {
////	        LOG.info( "user."+id);
////	        return transctionSErvices.getTranctionById(id);
////	    }
////	    @RequestMapping(value = "/getAllTransction/", method = RequestMethod.GET)
////	    public List<Transcation> getAllTransction() {
////	        LOG.info( "user.");
////	        return transctionSErvices.getAllTranction();
////	    }
////	    
////	    @RequestMapping(value = "/getWalletById/{id}/", method = RequestMethod.GET)
////	    public Wallet getWalletById(@PathVariable int id) {
////	        LOG.info( "user."+id);
////	        return salesRepository.getWalletById(id);
////	    }
////	    @RequestMapping(value = "/updateWallet", method = RequestMethod.PATCH)
////	    public Wallet updateWallet(@PathVariable Wallet wallet) {
////	        LOG.info( "user."+wallet.getId());
////	        return salesRepository.updateWallet(wallet);
////	    }
////	    @RequestMapping(value = "/getAllWallet", method = RequestMethod.GET)
////	    public List<Wallet> getAllWallet() {
////	        //LOG.info( "user."+id);
////	        return salesRepository.getAllWallet();
////	    }
////	    
//////	    @RequestMapping(value = "/getUserbyPayments/", method = RequestMethod.GET)
//////	    public List<User> getUserbyPayments() {
//////	        LOG.info( "user.");
//////	        return salesRepository.getAllUserbypaymentsRep();
//////	    }
//////	    @RequestMapping(value = "/deleteAllUser", method = RequestMethod.DELETE)
//////	    public boolean deleteTransction() {
//////	        //LOG.info( "user."+transcation.getId());
//////	        
//////	        return salesRepository.deleteAllUser();
//////	    }
//	    @RequestMapping(value = "/addMoneyOnWallet", method = RequestMethod.POST)
//	    public UserWallet addMoneyOnWallet(@RequestBody UserTranscation transcation) {
//	        LOG.info("Transction user."+transcation.getId());
//	       User user= usml.getSubUser(transcation.getUserId());
//	     //  LOG.info("Transction user."+transcation.toString()+"user "+user.toString());
//	      // UserType ut=UserType.ONEMONTH;
//	       LOG.info("Transction user."+transcation.getTXNAMOUNT());
//	       //System.out.println(t);
//	       //LOG.info("Transction user."+transctionSErvices.addTranction(transcation).toString());
//	      UserWallet ul=userWalletServices.getWalletByUserId(transcation.getUserId());
//	      Long d=(long) 0;
//	      Long cn=(long) 0;
//	       if(transcation.getTXNAMOUNT()>(long)0)
//	       {
//	    	   d=ul.getTotalAmount();
//	    	   cn=ul.getTotalCoins();
//	    	   List<UserPayment> pt=ul.getPayments();
//	    	   
//	    	   ul.setSubscriptionType(PaymentType.CREDIT.value);
//	    	   
//	    	   UserPayment pm;
//	    	   if(pt==null)
//	    	   {
//	    		   pt=new ArrayList<UserPayment>();
//	    		   pm=new UserPayment(transcation.getId(), transcation.getTXNAMOUNT(),OttUtil.amountToCoins(transcation.getTXNAMOUNT()), "Money Added to Wallet", transcation.getTXNID(), transcation.getUserId(), LocalDate.now(), LocalDate.now(), PaymentType.CREDIT);
////	    		   pm=new Payment(id, amount, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
////	    		 //  pm=new UserPayment(transcation.getId(),transcati0on.getTXNAMOUNT(),tra,"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
//	    		  pt.add(pm);
//	    	   }else
//	    	   {
//	    		  // pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
//	    		   pm=new UserPayment(transcation.getId(), transcation.getTXNAMOUNT(),OttUtil.amountToCoins(transcation.getTXNAMOUNT()), "Money Added to Wallet", transcation.getTXNID(), transcation.getUserId(), LocalDate.now(), LocalDate.now(), PaymentType.CREDIT);
//
//	    		   pt.add(pm);  
//	    	   }
//	    	   
//	    	   ul.setPayments(pt);
//	    	   d=(long) (d+transcation.getTXNAMOUNT());
//	    	   cn=cn+OttUtil.amountToCoins((long)transcation.getTXNAMOUNT());
//	    	   ul.setTotalAmount(d);
//	    	   ul.setTotalCoins(cn);
//	       }
//	       
//	     //  user.setUserPermList(trs);
//	   userTranscationServices.addTranction(transcation);
////	  
//	  return userWalletServices.updateWallet(ul);
//	       // return usml.updateUserWithId(user);
//	    }
////	    
////	    @RequestMapping(value = "/deleteUserTransction", method = RequestMethod.DELETE)
////	    public String deleteUserTransction(@RequestBody UserTranscation transcation) {
////	        LOG.info( "user."+transcation.getId());
////	        userTranscationServices.deleteTranction(transcation);
////	        return null;
////	    }
////	    @RequestMapping(value = "/getUserTransctionById/{id}/", method = RequestMethod.GET)
////	    public UserTranscation getUserTransction(@PathVariable String id) {
////	        LOG.info( "user."+id);
////	        return userTranscationServices.getTranctionById(id);
////	    }
////	    @RequestMapping(value = "/getAllUserTransction/", method = RequestMethod.GET)
////	    public List<UserTranscation> getAllUserTransction() {
////	        LOG.info( "user.");
////	        return userTranscationServices.getAllTranction();
////	    }
////	    
////	    @RequestMapping(value = "/getUserWalletById/{id}/", method = RequestMethod.GET)
////	    public UserWallet getUserWalletById(@PathVariable int id) {
////	        LOG.info( "user."+id);
////	        return userWalletServices.getWalletById(id);
////	    }
////	    @RequestMapping(value = "/updateUserWallet", method = RequestMethod.PATCH)
////	    public UserWallet updateUserWallet(@PathVariable UserWallet wallet) {
////	        LOG.info( "user."+wallet.getId());
////	        return userWalletServices.updateWallet(wallet);
////	    }
////	    @RequestMapping(value = "/getAllUserWallet", method = RequestMethod.GET)
////	    public List<UserWallet> getAllUserWallet() {
////	        //LOG.info( "user."+id);
////	        return userWalletServices.getAllWallet();
////	    }
////	    @RequestMapping(value = "/clearAllDb", method = RequestMethod.GET)
////	    public void clearAllDb() {
////	        //LOG.info( "user."+id);
////	        singleVideoServicesImplimentation.deleteAll();
////	        userWalletServices.deleteWalletAll();
////	        salesRepository.deleteWalletAll();
////	        
////	        
////	    }
////	    @RequestMapping(value = "/redemiCoins/{walletId}/{coinValue}/{vodId}", method = RequestMethod.PATCH)
////	public ResponseEntity<?> redemiCoins(@PathVariable int walletId,@PathVariable Long coinValue,@PathVariable int vodId, UriComponentsBuilder ucBuilder) {
////	        //LOG.info( "user."+id);
////	    	   LOG.info("Transction user."+walletId);
////		       User user= usml.getSubUser(walletId);
////		     //  LOG.info("Transction user."+transcation.toString()+"user "+user.toString());
////		      // UserType ut=UserType.ONEMONTH;
////		       LOG.info("Transction user."+walletId);
////		       //System.out.println(t);
////		       //LOG.info("Transction user."+transctionSErvices.addTranction(transcation).toString());
////		      UserWallet ul=userWalletServices.getWalletByUserId(walletId);
////		      SingleVideo sv=singleVideoServicesImplimentation.getVideoById(vodId);
////		      Wallet cwl= salesRepository.getWalletById(sv.getCreatorId());
////		      Long d=(long) 0;
////		      Long cn=(long) 0;
////		      Long d1=(long) 0;
////		      Long cn1=(long) 0;
////		      List<Payment> pt1=new ArrayList<Payment>();
////		      List<UserPayment> pt=new ArrayList<UserPayment>();
////		       if(coinValue>(long)0)
////		       {
////		    	   d=ul.getTotalAmount();
////		    	   cn=ul.getTotalCoins();
////		    	   d1=cwl.getTotalAmount();
////		    	   cn1=cwl.getTotalcoins();
////		    	   pt=ul.getPayments();
////		    	  pt1 =cwl.getPayments();
////		    	   ul.setSubscriptionType(PaymentType.CREDIT.value);
////		    	   
////		    	   UserPayment pm;
////		    	   Payment pm1;
////		    	   if(cn>=coinValue) {
////		    		   d=(long) (d-OttUtil.coinsToAmount(coinValue));
////			    	   cn=cn-coinValue;
////			    	   d1=(long) (d1+OttUtil.coinsToAmount(coinValue));
////			    	   cn1=cn1+coinValue;
////			    	   cwl.setTotalAmount(d1);
////			    	   cwl.setTotalcoins(cn1);
////			    	   ul.setTotalAmount(d);
////			    	   ul.setTotalCoins(cn);
////		    	   }else
////		    	   {
////		    		   return new ResponseEntity(new CustomErrorType("We don't found Sufficient Funds In Your Wallet Associated With Mobile"+user.getPhone()            ),HttpStatus.CONFLICT);
////		    	   }
////		    	  
////		    	 
////		    	   int uvId=(LocalDate.now().toString()+""+walletId).hashCode();
////		    	   if(pt1==null)
////		    	   {
////		    		   pt1=new ArrayList<Payment>();
////		    		   pm1=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),OttUtil.coinsToAmount(coinValue),coinValue,"payment for Live "+uvId,String.valueOf(vodId),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,vodId);
//////		    		   pm=new Payment(id, amount, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
//////		    		 //  pm=new UserPayment(transcation.getId(),transcation.getTXNAMOUNT(),tra,"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pt1.add(pm1);
////		    	   }else
////		    	   {
////		    		  // pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pm1=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),OttUtil.coinsToAmount(coinValue),coinValue,"payment for Live "+uvId,String.valueOf(vodId),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,vodId);
////
////		    		   pt1.add(pm1);  
////		    	   }
////		    	   if(pt==null)
////		    	   {
////		    		   pt=new ArrayList<UserPayment>();
////		    		   pm=new UserPayment(uvId, OttUtil.coinsToAmount(coinValue),coinValue, "Coin Redim for"+OttUtil.coinsToRedimType(coinValue), uvId+"", user.getId(), LocalDate.now(), LocalDate.now(), PaymentType.DEBIT);
//////		    		   pm=new Payment(id, amount, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
//////		    		 //  pm=new UserPayment(transcation.getId(),transcation.getTXNAMOUNT(),tra,"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pt.add(pm);
////		    	   }else
////		    	   {
////		    		  // pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		   pm=new UserPayment(uvId, OttUtil.coinsToAmount(coinValue),coinValue, "Coin Redim for"+OttUtil.coinsToRedimType(coinValue), uvId+"", user.getId(), LocalDate.now(), LocalDate.now(), PaymentType.DEBIT);
////
////		    		   pt.add(pm);  
////		    	   }
////		    	   cwl.setPayments(pt1);
////		    	   ul.setPayments(pt);
////		       }
////		       CoinTranscation ctn=null;
////		       Transcation trns=null;
////		       List<Integer> ur=user.getUserPermList();
////		       if(OttUtil.coinsToRedimType(coinValue)==CoinRedemiType.LIVE)
////		       {
////		    	   trns=new Transcation(0, "", "ok", UUID.randomUUID().toString(), "NIght Leap Wallet", UUID.randomUUID().toString(), OttUtil.coinsToAmount(coinValue), LocalDate.now().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "200", "ONLINE", UUID.randomUUID().toString(), "NIR", "NightLeap GateWay", "0k", user.getId(), sv.getCreatorId(), vodId);
////		    	  ctn= new CoinTranscation(0, user.getName()+" pay to"+vodId, CoinRedemiType.LIVE, String.valueOf(coinValue), user.getId(), vodId, LocalDate.now());
////		    	  ur=(ArrayList<Integer>) user.getUserPermList();
////		    	  
////		    	  if(ur!=null) {
////		    		  for(Integer i:ur)
////		    		  {
////		    			  if(i==vodId)
////		    			  {
////		    				  return new ResponseEntity(new CustomErrorType("You Already Subscribe To the Video Id"+vodId
////					                    ),HttpStatus.CONFLICT);
////		    			  }
////		    		  }
////		    		  ur.add(vodId);
////		    	  }else
////		    	  {
////		    		  ur=new ArrayList<Integer>();
////		    		  ur.add(vodId); 
////		    	  }
////		    	  
////		       }else if(OttUtil.coinsToRedimType(coinValue)==CoinRedemiType.PERSONALM)
////		       {
////		    	   trns=new Transcation(0, "", "ok", UUID.randomUUID().toString(), "NIght Leap Wallet", UUID.randomUUID().toString(), OttUtil.coinsToAmount(coinValue), LocalDate.now().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "200", "ONLINE", UUID.randomUUID().toString(), "NIR", "NightLeap GateWay", "0k", user.getId(), sv.getCreatorId(), vodId);
////
////		    	   ctn= new CoinTranscation(0, user.getName()+" pay to"+vodId, CoinRedemiType.PERSONALM, String.valueOf(coinValue), user.getId(), vodId, LocalDate.now());
//// 
////		       }else if(OttUtil.coinsToRedimType(coinValue)==CoinRedemiType.STICKER)
////		       {
////		    	   trns=new Transcation(0, "", "ok", UUID.randomUUID().toString(), "NIght Leap Wallet", UUID.randomUUID().toString(), OttUtil.coinsToAmount(coinValue), LocalDate.now().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "200", "ONLINE", UUID.randomUUID().toString(), "NIR", "NightLeap GateWay", "0k", user.getId(), sv.getCreatorId(), vodId);
////
////		    	   ctn= new CoinTranscation(0, user.getName()+" pay to"+vodId, CoinRedemiType.STICKER, String.valueOf(coinValue), user.getId(), vodId, LocalDate.now());
//// 
////		       }else if(OttUtil.coinsToRedimType(coinValue)==CoinRedemiType.REQUEST)
////		       {
////		    	   trns=new Transcation(0, "", "ok", UUID.randomUUID().toString(), "NIght Leap Wallet", UUID.randomUUID().toString(), OttUtil.coinsToAmount(coinValue), LocalDate.now().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "200", "ONLINE", UUID.randomUUID().toString(), "NIR", "NightLeap GateWay", "0k", user.getId(), sv.getCreatorId(), vodId);
////
////		    	   ctn= new CoinTranscation(0, user.getName()+" pay to"+vodId, CoinRedemiType.REQUEST, String.valueOf(coinValue), user.getId(), vodId, LocalDate.now());
////
////		       }
////		      
////		     //  Payment payment=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
////		       
////		       user.setUserPermList(ur);
////		      transctionSErvices.addTranction(trns);
////		      salesRepository.updateWallet(cwl);
////		     //  user.setUserPermList(trs);
////		  coinTranscation.addTranction(ctn);
////		  userWalletServices.updateWallet(ul);
////		
////		 return new ResponseEntity( usml.updateUser(user),HttpStatus.ACCEPTED);
////
////
////
////	    }
////	    @RequestMapping(value = "/redemiCoinsProfileContent/{walletId}/{coinValue}/{profileContentId}", method = RequestMethod.PATCH)
////	public ResponseEntity<?> redemiCoinsProfileContent(@PathVariable int walletId,@PathVariable Long coinValue,@PathVariable int profileContentId, UriComponentsBuilder ucBuilder) {
////	        //LOG.info( "user."+id);
////	    	   LOG.info("Transction user."+walletId+" "+coinValue+" "+profileContentId);
////		       User user= usml.getSubUser(walletId);
////		     //  LOG.info("Transction user."+transcation.toString()+"user "+user.toString());
////		      // UserType ut=UserType.ONEMONTH;
////		       LOG.info("Transction user."+walletId);
////		       //System.out.println(t);
////		       //LOG.info("Transction user."+transctionSErvices.addTranction(transcation).toString());
////		      UserWallet ul=userWalletServices.getWalletByUserId(walletId);
////		      ProfileContent sv=profileContentServices.getProfileContentById(profileContentId);
////		      Wallet cwl= salesRepository.getWalletById(sv.getCreatorId());
////		      Long d=(long) 0;
////		      Long cn=(long) 0;
////		      Long d1=(long) 0;
////		      Long cn1=(long) 0;
////		      List<Payment> pt1=new ArrayList<Payment>();
////		      List<UserPayment> pt=new ArrayList<UserPayment>();
////		       if(coinValue>(long)0)
////		       {
////		    	   d=ul.getTotalAmount();
////		    	   cn=ul.getTotalCoins();
////		    	   d1=cwl.getTotalAmount();
////		    	   cn1=cwl.getTotalcoins();
////		    	   pt=ul.getPayments();
////		    	  pt1 =cwl.getPayments();
////		    	   ul.setSubscriptionType(PaymentType.CREDIT.value);
////		    	   
////		    	   UserPayment pm;
////		    	   Payment pm1;
////		    	   if(cn>=coinValue) {
////		    		   d=(long) (d-OttUtil.coinsToAmount(coinValue));
////			    	   cn=cn-coinValue;
////			    	   d1=(long) (d1+OttUtil.coinsToAmount(coinValue));
////			    	   cn1=cn1+coinValue;
////			    	   cwl.setTotalAmount(d1);
////			    	   cwl.setTotalcoins(cn1);
////			    	   ul.setTotalAmount(d);
////			    	   ul.setTotalCoins(cn);
////		    	   }else
////		    	   {
////		    		   return new ResponseEntity(new CustomErrorType("We don't found Sufficient Funds In Your Wallet Associated With Mobile"+user.getPhone()            ),HttpStatus.CONFLICT);
////		    	   }
////		    	  
////		    	 
////		    	   int uvId=(LocalDate.now().toString()+""+walletId).hashCode();
////		    	   
////		    	   if(pt1==null)
////		    	   {
////		    		   pt1=new ArrayList<Payment>();
////		    		   pm1=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),coinValue,coinValue,"payment for Live "+uvId,String.valueOf(profileContentId),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,profileContentId);
//////		    		   pm=new Payment(id, amount, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
//////		    		 //  pm=new UserPayment(transcation.getId(),transcation.getTXNAMOUNT(),tra,"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pt1.add(pm1);
////		    	   }else
////		    	   {
////		    		  // pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pm1=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),coinValue,coinValue,"payment for Live "+uvId,String.valueOf(profileContentId),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,profileContentId);
////
////		    		   pt1.add(pm1);  
////		    	   }
////		    	   if(pt==null)
////		    	   {
////		    		   pt=new ArrayList<UserPayment>();
////		    		   pm=new UserPayment(uvId, coinValue,coinValue, "Coin Redim for"+OttUtil.coinsToRedimType(coinValue), uvId+"", user.getId(), LocalDate.now(), LocalDate.now(), PaymentType.DEBIT);
//////		    		   pm=new Payment(id, amount, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
//////		    		 //  pm=new UserPayment(transcation.getId(),transcation.getTXNAMOUNT(),tra,"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		  pt.add(pm);
////		    	   }else
////		    	   {
////		    		  // pm=new Payment(transcation.getId(),transcation.getTXNAMOUNT(),"payment for Live"+transcation.getBrodcastId(),String.valueOf(transcation.getBrodcastId()),user.getName(),LocalDate.now(),LocalDate.now(),PaymentType.CREDIT,transcation.getBrodcastId());
////		    		   pm=new UserPayment(uvId, coinValue,coinValue, "Coin Redim for"+OttUtil.coinsToRedimType(coinValue), uvId+"", user.getId(), LocalDate.now(), LocalDate.now(), PaymentType.DEBIT);
////
////		    		   pt.add(pm);  
////		    	   }
////		    	   cwl.setPayments(pt1);
////		    	   ul.setPayments(pt);
////		       }
////		       CoinTranscation ctn=null;
////		       Transcation trns=null;
////		       List<Integer> ur=user.getProfileAccessList();
////		     
////		    	   trns=new Transcation(0, "", "ok", UUID.randomUUID().toString(), "NIght Leap Wallet", UUID.randomUUID().toString(), OttUtil.coinsToAmount(coinValue), LocalDate.now().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), "200", "ONLINE", UUID.randomUUID().toString(), "NIR", "NightLeap GateWay", "0k", user.getId(), sv.getCreatorId(), profileContentId);
////		    	  ctn= new CoinTranscation(0, user.getName()+" pay to"+profileContentId, CoinRedemiType.DEFAULT, String.valueOf(coinValue), user.getId(), profileContentId, LocalDate.now());
////		    	  ur=(ArrayList<Integer>) user.getProfileAccessList();
////		    	  
////		    	  if(ur!=null) {
////		    		  for(Integer i:ur)
////		    		  {
////		    			  if(i==profileContentId)
////		    			  {
////		    				  return new ResponseEntity(new CustomErrorType("You Already Subscribe To the Content Id"+profileContentId
////					                    ),HttpStatus.CONFLICT);
////		    			  }
////		    		  }
////		    		  ur.add(profileContentId);
////		    	  }else
////		    	  {
////		    		  ur=new ArrayList<Integer>();
////		    		  ur.add(profileContentId); 
////		    	  }
////		      
////		     //  Payment payment=new Payment((user.getId()+""+LocalDate.now().toString()).hashCode(),, paymentTitle, liveName, createdBy, createdAt, updateAt, paymentType, liveId)
////		       
////		       user.setProfileAccessList(ur);
////		      transctionSErvices.addTranction(trns);
////		      salesRepository.updateWallet(cwl);
////		     //  user.setUserPermList(trs);
////		  coinTranscation.addTranction(ctn);
////		  userWalletServices.updateWallet(ul);
////		
////		 return new ResponseEntity( usml.updateUser(user),HttpStatus.ACCEPTED);
////
////
////
////	    }
////	    
////	    
////
//}
