package com.gamotrance.OTT.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.HomePagerContain;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.services.FileStorageService;
import com.gamotrance.OTT.services.HelpServices;
import com.gamotrance.OTT.services.HomePagerContainerServices;
import com.gamotrance.OTT.services.LikesServices;
import com.gamotrance.OTT.services.SingleVideoServicesImplimentation;
import com.gamotrance.OTT.services.UserViewServices;
import com.gamotrance.OTT.Model.AppUpdate;
import com.gamotrance.OTT.Model.Help;
import com.gamotrance.OTT.services.AppUpdateServices;

@RestController
@RequestMapping(value = "/DashBoardController")
public class SliderController {

	private final SingleVideoServicesImplimentation singleVideoServicesImplimentation;

	private HomePagerContainerServices homePagerContainerServices;

	private AppUpdateServices appUpdateServices;

	private HelpServices helpServices;

	public SliderController(HelpServices helpServices, AppUpdateServices appUpdateServices,
			SingleVideoServicesImplimentation singleVideoServicesImplimentation,
			HomePagerContainerServices homePagerContainerServices) {
		this.singleVideoServicesImplimentation = singleVideoServicesImplimentation;
		this.homePagerContainerServices = homePagerContainerServices;
		this.appUpdateServices = appUpdateServices;
		this.helpServices = helpServices;

	}

	@RequestMapping(value = "/addtoDashBoard/{videoId}/{expDate}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String addToDashBoard(@PathVariable int videoId, @PathVariable String expDate) {
		System.out.println("response videoId:" + videoId + " ExpDate:" + expDate);
		SingleVideo sv = singleVideoServicesImplimentation.getVideoById(videoId);
		System.out.println("response Id:" + sv.getId());
		System.out.println("response Id:" + sv.getId());
		String st = homePagerContainerServices
				.addToHomePagerContain(new HomePagerContain(videoId, expDate, LocalDate.now().toString(), "", videoId));

		System.out.println("response Id:" + st);
		return st;
	}

	@RequestMapping(value = "/getDashBoard", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<SingleVideo> getDashBoard() {

		List<HomePagerContain> hm = homePagerContainerServices.getHomePagerContainer();
		ArrayList<SingleVideo> al = new ArrayList<SingleVideo>();
		for (HomePagerContain h : hm) {
			al.add(singleVideoServicesImplimentation.getVideoById(h.getVideoId()));
		}
		homePagerContainerServices.verifyHomePager();
		Collections.reverse(al);
		return al;
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String deleteAll() {
		return homePagerContainerServices.deleteAll();
	}

	@RequestMapping(value = "/deleteSingleDash/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String deleteSingleDash(@PathVariable int id) {
		return homePagerContainerServices.deleteSingleDash(id);
	}

	@RequestMapping(value = "/addAppUpdate", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public AppUpdate addAppUpdate(@RequestBody AppUpdate appUpdate) {

		return appUpdateServices.addAppUpdate(appUpdate);
	}

	@RequestMapping(value = "/getAppUpdate", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public AppUpdate getAppUpdate() {

		return appUpdateServices.getAppUpdate();
	}

	@RequestMapping(value = "/deleteAllUpdate", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String deleteAllUpdate() {
		return appUpdateServices.deleteAll();
	}

	@RequestMapping(value = "/addHelp", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public Help addAppUpdate(@RequestBody Help help) {

		return helpServices.addHelp(help);
	}

	@RequestMapping(value = "/getByListHelp/{userId}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<Help> getByListHelp(@PathVariable int userId) {

		return helpServices.getByListHelp(userId);
	}

	@RequestMapping(value = "/getHelpById/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<Help> getHelpById(@PathVariable int id) {

		return helpServices.getHelpById(id);
	}

	@RequestMapping(value = "/getByListHelp", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<Help> getByListHelp() {

		return helpServices.getHelpALL();
	}

	@RequestMapping(value = "/getHelpByIds/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public Help getHelpByIds(@PathVariable int id) {

		return helpServices.getHelpByIds(id);
	}

	@RequestMapping(value = "/deleteHelp", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean getHelp(@PathVariable Help help) {

		return helpServices.deleteHelp(help);
	}
	// getHelpByIds
}
