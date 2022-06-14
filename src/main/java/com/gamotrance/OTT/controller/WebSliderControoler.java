package com.gamotrance.OTT.controller;

import org.springframework.stereotype.Controller;

import com.gamotrance.OTT.services.HomePagerContainerServices;
import com.gamotrance.OTT.services.SingleVideoServicesImplimentation;

@Controller
public class WebSliderControoler {

	SingleVideoServicesImplimentation singleVideoServicesImplimentation;

	HomePagerContainerServices homePagerContainerServices;

	public WebSliderControoler(SingleVideoServicesImplimentation singleVideoServicesImplimentation,
			HomePagerContainerServices homePagerContainerServices) {
		super();
		this.singleVideoServicesImplimentation = singleVideoServicesImplimentation;
		this.homePagerContainerServices = homePagerContainerServices;
	}

}
