package com.gamotrance.OTT.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gamotrance.OTT.OttUtil;
import com.gamotrance.OTT.Model.Checksum;
import com.gamotrance.OTT.Model.Payment;
import com.gamotrance.OTT.Model.PaymentCapture;
import com.gamotrance.OTT.Model.PaymentRPay;
import com.gamotrance.OTT.Model.RentAccess;
import com.gamotrance.OTT.Model.RentAccessPay;
import com.gamotrance.OTT.Model.RentMovie;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.Transcation;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserType;
import com.gamotrance.OTT.services.RentAccessServices;
import com.gamotrance.OTT.services.RentMovieServices;
import com.gamotrance.OTT.services.TransctionSErvices;
import com.google.gson.Gson;
import com.paytm.pg.merchant.PaytmChecksum;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping(value = "/RentMovieController")
public class RentMovieController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private static String MID = "mBSVRT27943668214901";
	private static String MercahntKey = "AL5eo2xV_yOwMLIf";

	private final RentAccessServices rentAccessServices;
	private final RentMovieServices rentMovieServices;
	private final TransctionSErvices transctionSErvices;

	public RentMovieController(TransctionSErvices transctionSErvices, RentAccessServices rentAccessServices,
			RentMovieServices rentMovieServices) {
		super();
		this.rentAccessServices = rentAccessServices;
		this.rentMovieServices = rentMovieServices;
		this.transctionSErvices = transctionSErvices;
	}

	@Cacheable(value = "getAllRentMovie")
	@RequestMapping(value = "/getAllRentMovie", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<RentMovie> getAllRentMovie() {
		return rentMovieServices.getAllRentMovie();
	}

//	 @Cacheable(value = "getAllRentMovie")
	@RequestMapping(value = "/getAllRent", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public MappingJacksonValue getAllRent() {
		SimpleBeanPropertyFilter sfb = SimpleBeanPropertyFilter.serializeAllExcept("directors", "eps", "writer");

		FilterProvider filter = new SimpleFilterProvider().addFilter("userfilet", sfb);

		List<RentMovie> rm = rentMovieServices.getAllRentMovie();
		MappingJacksonValue mpj = new MappingJacksonValue(rm);
		mpj.setFilters(filter);
		return mpj;
	}

//     @RequestMapping(value = "/getAllVideoByCountry", method = RequestMethod.GET)
// 	public List<SingleVideo> getAllSingleVideo(String country){
//   	  List<SingleVideo> list=new ArrayList<SingleVideo>();
//   	  for(int id:countryVideoDecoedServices.getHomePagerContainer(country))
//   	  {
//   		  list.add(salesRepository.getVideoById(id));
//   	  }
// 		return list;
// 	}
	// @Cacheable(value = "rentmovie", key = "#rentMovie.userId")
	@RequestMapping(value = "/getRentMovieById/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public RentMovie getRentMovieById(@PathVariable int id) {
		return rentMovieServices.getRentMovieById(id);
	}

	@RequestMapping(value = "/getAllRentAccess", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<RentAccess> getAllRentAccess() {
		return rentAccessServices.getAllRentAccess();
	}

	@RequestMapping(value = "/deleteAllRentMovie", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteAllRentMovie() {
		return rentMovieServices.deleteAllRentMovie();
	}

//   @CachePut(value = "rentmovie", key = "#rentMovie.userId")
	@RequestMapping(value = "/rentMovie/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public RentMovie updateRentMovie(@RequestBody RentMovie rentMovie) {
		return rentMovieServices.updateRentMovie(rentMovie);
	}

//   @CacheEvict(value = "getAllRentMovie")
	@RequestMapping(value = "/deleteAllRentAccess", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteAllRentAccess() {
		return rentAccessServices.deleteAllRentAccess();
	}

//   @CacheEvict(value = "rentmovie", key = "#rentMovie.userId")
	@RequestMapping(value = "/deleteRentAccess", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteRentAccess(@RequestBody RentAccess restAccess) {
		return rentAccessServices.deleteRentAccess(restAccess);
	}

	// @Cacheable(value = "rentmovie", key = "#userId")
	// @Cacheable(value = "rentmovie", key = "#id", unless = "#result.likes < 500")
	@RequestMapping(value = "/getRentMovieById/{userId}/{vodid}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public RentMovie getRentMovieById(@PathVariable String userId, @PathVariable String vodid) {
		System.out.println(rentAccessServices.getRentAccessByUserId(userId, vodid));
		if (rentAccessServices.getRentAccessByUserId(userId, vodid)) {
			return rentMovieServices.getRentMovieById(Integer.valueOf(vodid));
		} else {
			return null;
		}
	}

	// @Cacheable(value = "rentmovie", key = "#id", unless = "#result.likes < 500")
	@RequestMapping(value = "/getRentMovieByUserId/{userId}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<RentMovie> getRentMovieByUserId(@PathVariable String userId) {
		List<RentMovie> rentList = new ArrayList<>();
		for (RentAccess rt : rentAccessServices.getRentAccessListById(userId)) {
			rentList.add(rentMovieServices.getRentMovieById(Integer.valueOf(rt.getVdoId())));
		}
		return rentList;
	}

	@RequestMapping(value = "/purchaseRentMovie", method = RequestMethod.POST)
	public boolean purchaseRentMovie(@RequestBody RentAccessPay pameCapture) throws RazorpayException {
		// LOG.info("Transction user."+transcation.getId());
		RentMovie user1 = rentMovieServices.getRentMovieById(Integer.valueOf(pameCapture.getVdoId()));
		LOG.info("Transction user." + pameCapture.toString() + "user " + user1.toString());
		LOG.info("Transction user." + pameCapture.getAmount());
		User user = null;
		LOG.info("Transction user." + pameCapture.getUserId());
		UserType ut = UserType.ONEMONTH;
		LOG.info("Transction user." + pameCapture.getAmount());
		if (pameCapture.getAmount() == 100) {
			ut = UserType.ONEMONTH;
		} else if (pameCapture.getAmount() == user1.getPrice().gettPrice()) {
			rentAccessServices.addRentAccess(new RentAccess(
					(pameCapture.getUserId() + "" + LocalDateTime.now().toString()).hashCode(), pameCapture.getVdoId(),
					String.valueOf(pameCapture.getUserId()), LocalDateTime.now().plusHours(24).toString()));
		} else if (pameCapture.getAmount() == user1.getPrice().getmPrice()) {
			rentAccessServices.addRentAccess(new RentAccess(
					(pameCapture.getUserId() + "" + LocalDateTime.now().toString()).hashCode(), pameCapture.getVdoId(),
					String.valueOf(pameCapture.getUserId()), LocalDateTime.now().plusDays(30).toString()));
		} else if (pameCapture.getAmount() == user1.getPrice().getyPrice()) {
			rentAccessServices.addRentAccess(new RentAccess(
					(pameCapture.getUserId() + "" + LocalDateTime.now().toString()).hashCode(), pameCapture.getVdoId(),
					String.valueOf(pameCapture.getUserId()), LocalDateTime.now().plusDays(360).toString()));
		}

		return true;
	}

	@RequestMapping(value = "/addRentVideo/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public RentMovie addRentVideo(@RequestBody RentMovie singleVideo) {
		// List<String> list=new Gson().fromJson(cW, new TypeToken<List<String>>()
		// {}.getType());
		// countryVideoDecoedServices.addCountryVideoDecoded(new
		// CountryVideoDecoded(singleVideo.getId(), list));
		return rentMovieServices.addRentMovie(singleVideo);
	}
}
