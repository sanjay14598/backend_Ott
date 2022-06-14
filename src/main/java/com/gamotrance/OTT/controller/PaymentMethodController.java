package com.gamotrance.OTT.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.PaymentMethod;
import com.gamotrance.OTT.Model.Plan;
import com.gamotrance.OTT.Model.TransctiionField;
import com.gamotrance.OTT.Model.UpiConstant;
import com.gamotrance.OTT.services.PaymentMethodSeervices;
import com.gamotrance.OTT.services.TranscationFieldServices;
import com.gamotrance.OTT.services.UpiConstantServices;

@RestController
@RequestMapping(value = "/PaymentMehod")
public class PaymentMethodController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final PaymentMethodSeervices paymentMethodSeervices;
	private final TranscationFieldServices transcationFieldServices;
	private final UpiConstantServices upiConstantServices;

	public PaymentMethodController(PaymentMethodSeervices paymentMethodSeervices,
			TranscationFieldServices transcationFieldServices, UpiConstantServices upiConstantServices) {
		super();
		this.paymentMethodSeervices = paymentMethodSeervices;
		this.transcationFieldServices = transcationFieldServices;
		this.upiConstantServices = upiConstantServices;
	}

	@RequestMapping(value = "/addPaymentMethod/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public PaymentMethod addPaymentMethod(@RequestBody PaymentMethod plan) {
		PaymentMethod plan1 = paymentMethodSeervices.addPaymentMethod(plan);
		// List<String> list=new Gson().fromJson(cW, new TypeToken<List<String>>()
		// {}.getType());
		// countryVideoDecoedServices.addCountryVideoDecoded(new
		// CountryVideoDecoded(singleVideo.getId(), list));
// 		 try {
//				flushall("redis-cli flushall");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		return plan1;
	}

// @Cacheable(value = "getPlanAll")
	@RequestMapping(value = "/getPaymentMethodAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<PaymentMethod> getAllPaymentMethod() {
		return paymentMethodSeervices.getPaymentMethodAll();
	}

// @Cacheable(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/getPaymentMethodById", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public PaymentMethod getPaymentMethodById(@PathVariable int id) {
		return paymentMethodSeervices.getPaymentMethodByIds(id);
	}

//  @CachePut(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/PaymentMethod/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://localhost:4200")
	public PaymentMethod updatePaymentMethod(@RequestBody PaymentMethod plan) {
		return paymentMethodSeervices.updatePaymentMethod(plan);
	}

//   @CacheEvict(value = "getPlanAll")
	@RequestMapping(value = "/deleteAllPaymentMethod", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteAllPaymentMethod() {
		return paymentMethodSeervices.deleteAllPaymentMethod();
	}

//   @CacheEvict(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/deletePaymentMethod", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deletePlan(@RequestBody PaymentMethod plan) {
		return paymentMethodSeervices.deletePaymentMethod(plan);
	}

	@RequestMapping(value = "/addTransctiionField/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public TransctiionField addTransctiionField(@RequestBody TransctiionField plan) {
		TransctiionField plan1 = transcationFieldServices.addTransctiionField(plan);
		// List<String> list=new Gson().fromJson(cW, new TypeToken<List<String>>()
		// {}.getType());
		// countryVideoDecoedServices.addCountryVideoDecoded(new
		// CountryVideoDecoded(singleVideo.getId(), list));
// 		 try {
//				flushall("redis-cli flushall");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		return plan1;
	}

// @Cacheable(value = "getPlanAll")
	@RequestMapping(value = "/getTransctiionFieldAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<TransctiionField> getAllTransctiionField() {
		return transcationFieldServices.getTransctiionFieldAll();
	}

// @Cacheable(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/getTransctiionFieldById", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public TransctiionField getTransctiionFieldById(@PathVariable int id) {
		return transcationFieldServices.getTransctiionFieldByIds(id);
	}

//  @CachePut(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/TransctiionField/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://localhost:4200")
	public TransctiionField updateTransctiionField(@RequestBody TransctiionField plan) {
		return transcationFieldServices.updateTransctiionField(plan);
	}

//   @CacheEvict(value = "getPlanAll")
	@RequestMapping(value = "/deleteAllTransctiionField", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteAllTransctiionField() {
		return transcationFieldServices.deleteAllTransctiionField();
	}

//   @CacheEvict(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/deleteTransctiionField", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteTransctiionField(@RequestBody TransctiionField plan) {
		return transcationFieldServices.deleteTransctiionField(plan);
	}

	@RequestMapping(value = "/addUpiConstant/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public UpiConstant addUpiConstant(@RequestBody UpiConstant plan) {
		UpiConstant plan1 = upiConstantServices.addUpiConstant(plan);
		// List<String> list=new Gson().fromJson(cW, new TypeToken<List<String>>()
		// {}.getType());
		// countryVideoDecoedServices.addCountryVideoDecoded(new
		// CountryVideoDecoded(singleVideo.getId(), list));
//		 try {
//				flushall("redis-cli flushall");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		return plan1;
	}

//@Cacheable(value = "getPlanAll")
	@RequestMapping(value = "/getAllUpiConstant", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UpiConstant> getAllUpiConstant() {
		return upiConstantServices.getUpiConstantAll();
	}

//@Cacheable(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/getUpiConstantById", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public UpiConstant getUpiConstantById(@PathVariable int id) {
		return upiConstantServices.getUpiConstantByIds(id);
	}

//@CachePut(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/UpiConstant/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://localhost:4200")
	public UpiConstant updateUpiConstant(@RequestBody UpiConstant plan) {
		return upiConstantServices.updateUpiConstant(plan);
	}

//@CacheEvict(value = "getPlanAll")
	@RequestMapping(value = "/deleteAllUpiConstant", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteAllUpiConstant() {
		return upiConstantServices.deleteAllUpiConstant();
	}

//@CacheEvict(value = "plan", key = "#plan.id")
	@RequestMapping(value = "/deleteUpiConstant", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteUpiConstant(@RequestBody UpiConstant plan) {
		return upiConstantServices.deleteUpiConstant(plan);
	}

}
