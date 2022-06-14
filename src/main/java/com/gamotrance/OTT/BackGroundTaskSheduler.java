package com.gamotrance.OTT;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gamotrance.OTT.services.HomePagerContainerServices;
import com.gamotrance.OTT.services.RentAccessServices;
import com.gamotrance.OTT.services.UserServicesImplimentation;

@Component
public class BackGroundTaskSheduler {

	private final HomePagerContainerServices headRequestService;

	private final UserServicesImplimentation userServices;

	private final RentAccessServices rentAccessServices;

	public BackGroundTaskSheduler(RentAccessServices rentAccessServices, HomePagerContainerServices headRequestService,
			UserServicesImplimentation userServices) {
		this.rentAccessServices = rentAccessServices;
		this.headRequestService = headRequestService;
		this.userServices = userServices;
	}


	@Scheduled(cron = "0 0 12 * * ?")
	public void getHeadValue() {
		headRequestService.verifyHomePager();
		userServices.validateUser();
		rentAccessServices.updateAccess();
		System.out.println("Background Updated");
	}

}
