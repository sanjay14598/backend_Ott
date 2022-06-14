package com.gamotrance.OTT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.PushNotificationRequest;
import com.gamotrance.OTT.Model.PushNotificationResponse;
import com.gamotrance.OTT.Model.UserNotif;
import com.gamotrance.OTT.services.PushNotificationService;
import com.gamotrance.OTT.services.UserNotifServices;

@RestController
public class PushNotificationController {  
	
	 private PushNotificationService pushNotificationService;
	    
	    public PushNotificationController(PushNotificationService pushNotificationService) {
	        this.pushNotificationService = pushNotificationService;
	    }
	    
	    @PostMapping("/notification/token")
	    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
	       // pushNotificationService.sendPushNotificationToToken(request);
	        System.out.println("princr");
	        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	    }
//	private PushNotificationService pushNotificationService;
//	private UserNotifServices userNotifServices;
//	public PushNotificationController(PushNotificationService pushNotificationService,UserNotifServices userNotifServices) {
//        this.pushNotificationService = pushNotificationService;
//        this.userNotifServices=userNotifServices;
//    }  
//	
//	 @RequestMapping(value = "/notification/topic",method = RequestMethod.POST)
//    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotificationWithoutData(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }  
//    @RequestMapping(value = "/notification/token",method = RequestMethod.POST)
//    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotificationToToken(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }   
//    @RequestMapping(value = "/notification/data",method = RequestMethod.POST)
//    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
//        pushNotificationService.sendPushNotification(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    } 
//    @RequestMapping(value = "/notification/updateToken",method = RequestMethod.PATCH)
//    public ResponseEntity updateToken(@RequestBody UserNotif request) {
//        userNotifServices.updateToken(request);
//        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "User Token Update has been done."), HttpStatus.OK);
//    } 

    
}