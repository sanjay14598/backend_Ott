package com.gamotrance.OTT.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.DBanner;
import com.gamotrance.OTT.services.DBannerServices;

@RestController
@RequestMapping(value = "/BannerController")
public class BannerController {
	private DBannerServices dBannerServices;

	public BannerController(DBannerServices dBannerServices) {
		super();
		this.dBannerServices = dBannerServices;
	}

	@RequestMapping(value = "/addtoDashBoard", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public String addToDashBoard(@RequestBody DBanner dBanner) {
		dBannerServices.addDBanner(dBanner);
		System.out.println("response Id:");
		return "Added";
	}

	@RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public DBanner updateBanner(@RequestBody DBanner dBanner) {
		System.out.println("response Id:");
		return dBannerServices.updateDBanner(dBanner);
	}

	@RequestMapping(value = "/getDashBoard", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public List<DBanner> getDashBoard() {

		return dBannerServices.getDBannerALL();
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public String deleteAll() {
		dBannerServices.deleteAllDBanner();
		return "Deleted";
	}

	@RequestMapping(value = "/deleteSingleDash/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String deleteSingleDash(@PathVariable int id) {
		dBannerServices.deleteDBanner(id);
		return "deleted";
	}

}
