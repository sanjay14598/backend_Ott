package com.gamotrance.OTT.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.Plan;
import com.gamotrance.OTT.services.PlanServices;

@RestController
@RequestMapping(value = "/Plan")
public class PlanController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PlanServices planServices;

	public PlanController(PlanServices planServices) {

		this.planServices = planServices;
	}

	@RequestMapping(value = "/addPlan/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public Plan addPlan(@RequestBody Plan plan) {
		Plan plan1 = planServices.addPlan(plan);
		// List<String> list=new Gson().fromJson(cW, new TypeToken<List<String>>()
		// {}.getType());
		// countryVideoDecoedServices.addCountryVideoDecoded(new
		// CountryVideoDecoded(singleVideo.getId(), list));
//	 		 try {
//					flushall("redis-cli flushall");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		return plan1;
	}

//	 @Cacheable(value = "getPlanAll")
	@RequestMapping(value = "/getPlanAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Plan> getAllPlan() {
		return planServices.getPlanAll();
	}

//	 @Cacheable(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/getPlanById", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public Plan getPlanById(@PathVariable int id) {
		return planServices.getPlanByIds(id);
	}

//	  @CachePut(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/plan/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://localhost:4200")
	public Plan updateRentMovie(@RequestBody Plan plan) {
		return planServices.updatePlan(plan);
	}

//	   @CacheEvict(value = "getPlanAll")
	@RequestMapping(value = "/deleteAllPlan", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteAllPlan() {
		return planServices.deleteAllPlan();
	}

//	   @CacheEvict(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/deletePlan", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deletePlan(@RequestBody Plan plan) {
		return planServices.deletePlan(plan);
	}

	public void flushall(String url) throws Exception {
		printOutput errorReported, outputMessage;
		String cmd = url;
		System.out.println(cmd);
		Runtime run = Runtime.getRuntime();
		Process pr = run.exec(cmd);
		errorReported = getStreamWrapper(pr.getErrorStream(), "ERROR");
		outputMessage = getStreamWrapper(pr.getInputStream(), "OUTPUT");
		errorReported.start();
		outputMessage.start();
		pr.waitFor();
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
		}
	}

	private class printOutput extends Thread {
		InputStream is = null;

		printOutput(InputStream is, String type) {
			this.is = is;
		}

		public void run() {
			String s = null;
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}

}
