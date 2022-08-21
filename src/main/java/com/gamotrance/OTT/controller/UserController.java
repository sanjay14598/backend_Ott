package com.gamotrance.OTT.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.stream.Collectors;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamotrance.OTT.CustomErrorType;
import com.gamotrance.OTT.OttUtil;
import com.gamotrance.OTT.Doa.UserRepo;
import com.gamotrance.OTT.Model.*;
import com.gamotrance.OTT.configs.JwtTokenProvider;
import com.gamotrance.OTT.services.CustomUserDetailsService;
import com.gamotrance.OTT.services.FileStorageService;
import com.gamotrance.OTT.services.OtpServices;
import com.gamotrance.OTT.services.PlanServices;
import com.gamotrance.OTT.services.TransctionSErvices;
import com.gamotrance.OTT.services.UserServicesImplimentation;
import com.google.api.client.json.Json;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.paytm.pg.merchant.PaytmChecksum;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/User")
public class UserController {
	private static final String UPLOADED_FOLDER = "/home/gulshan/Downloads/Isociety/";
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final UserServicesImplimentation salesRepository;
	private final TransctionSErvices transctionSErvices;
	private final OtpServices otpServices;
	private static String MID = "GnqWZs60135668160054";  //use Paytm MID
	private static String MercahntKey = "rr82qw5TMLHKd@Zw"; //use Paytm accessToken
	private static String INDUSTRY_TYPE_ID = "Retail";
	private static String CHANNLE_ID = "WAP";
	private static String WEBSITE = "DEFAULT";
	private static String CALLBACK_URL = "https://securegw.paytm.in/order/process";

	@Autowired
	private UserRepo userRepo;

	@Value("${file.upload-dir}")
	private String filRootDir;

	private FileStorageService fileStorageService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	private CustomUserDetailsService userService;
	private PlanServices plServices;

	public UserController(PlanServices plServices,
			OtpServices otpServices,
			CustomUserDetailsService userService,
			UserServicesImplimentation salesRepository,
			TransctionSErvices transctionSErvices,
			FileStorageService fileStorageService) {
		this.salesRepository = salesRepository;
		this.userService = userService;
		this.otpServices = otpServices;
		this.plServices = plServices;
		this.fileStorageService = fileStorageService;
		this.transctionSErvices = transctionSErvices;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity login(@RequestBody AuthBody data) {
		try {

			String username = data.getEmail();
			User loginUser = salesRepository.getUserByPhone(username);
			
			if(null == loginUser) {
				Map<Object, Object> model = new HashMap<>();
				model.put("code", 201);
				model.put("status", false);
				model.put("message", "User not found with " + loginUser.getPhone() +" mobile number");
			} 
			
			if (loginUser.getPassword().equalsIgnoreCase("key")) {
				Map<Object, Object> model = new HashMap<>();
				model.put("code", 204);
				model.put("status", true);
				model.put("message", "Please update your password");
				return ok(model);
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (!encoder.matches(data.getPassword(), loginUser.getPassword())) {
				Map<Object, Object> model = new HashMap<>();
				model.put("code", 202);
				model.put("status", true);
				model.put("message", "Password Not Match");
				return ok(model);
			}
		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, userService.findUserByPhone(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			System.out.println(token);

			model.put("user", salesRepository.getUserByPhone(username));
			model.put("code", 200);
			model.put("message", "OK");
			model.put("token", token);
			System.out.println(model.toString());
			return ok(model);
		} catch (AuthenticationException e) {
			Map<Object, Object> model = new HashMap<>();
			model.put("code", 500);
			model.put("status", false);
			model.put("message", "Something went worng please try again");
			throw new BadCredentialsException("Invalid email/password supplied");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/createAccount")
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity createAccount(@RequestBody User user) {
		User userExists = userService.findUserByPhone(user.getPhone());
		if (null != userExists) {
			throw new BadCredentialsException("User with mobile number: " + user.getPhone() + " already exists");
		}
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}
	
	@RequestMapping(value = "/getUserByID/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User getUserByID(@PathVariable int id) {
		
		return salesRepository.getSubUser(id);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUserByPhone/{phone}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity getUseerPhone(@PathVariable String phone) {
		Map<Object, Object> model = new HashMap<>();
		boolean ok = salesRepository.checkIfUserExist(phone);
		if (ok) {
			User u = salesRepository.getPhoneVerify(phone);
			model.put("user", u);
			model.put("status", ok);
			model.put("code", 200);
		} else {
			model.put("user", null);
			model.put("exist", ok);
			model.put("ResponseCode", 201);
		}
		return ok(model);
	}
	
	@RequestMapping(value = "/generateOtp/{phone}/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String UserOtpRequest(@PathVariable String phone) {

		String otp = OttUtil.OTP(4);
		LOG.info("get realted users." + phone + " the otp is:" + otp);
		otpServices.addAppUpdate(new Otp(phone.hashCode(), otp, phone));
		final String uri = "http://jskbulkmarketing.in/app/smsapi/index.php?key=460CA3C5F1005F&campaign=1&routeid=46&type=text&contacts="
				+ phone + "&senderid=ANGORR&msg=Your%20OTP%20to%20register%2Faccess%20Angoor%20digital%20is%20" + otp
				+ ".%20It%20will%20be%20valid%20for%203%20minutes.%20-%20Angoor%2FSapna%20films&template_id=1207162444792537966";
		System.out.println(uri);
		String result = "";
		try {
			HttpPost post = new HttpPost(uri);

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(post)) {

				System.out.println(EntityUtils.toString(response.getEntity()));
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {

		}
		return result;
	}

	@RequestMapping(value = "/validateOtp/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> validateOtp(@RequestBody Otp otp, UriComponentsBuilder ucBuilder) {
		LOG.info("get realted users." + otp.getMobile() + " the otp is:" + otp.getOtp());
		if (salesRepository.getPhoneExist("+91" + otp.getMobile())) {
			if (otpServices.getValidate(otp.getMobile(), otp.getOtp())) {
				return new ResponseEntity<User>(salesRepository.getPhoneVerify(otp.getMobile()), HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(new CustomErrorType("Worng Otp !!!"), HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<User>(
					salesRepository.createAccount(
							new User(otp.getMobile().hashCode(), "+91" + otp.getMobile(), UserType.BASIC)),
					HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> checkPassword(@RequestBody AuthBody ab) {
		LOG.info("get realted users." + ab.getEmail());
		User user = salesRepository.getSubUser(Integer.valueOf(ab.getEmail()));
		Map<Object, Object> model = new HashMap<>();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(ab.getPassword(), user.getPassword())) {
			model.put("statusCode", 206);
			model.put("status", "update");
			model.put("statusMobile", user.getPhone());
		} else {
			model.put("statusCode", 200);
			model.put("status", "updated");
			model.put("statusMobile", user.getPhone());
		}
		return ok(model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity updatePassword(@RequestBody AuthBody phone) {
		LOG.info("get phone Verify users." + phone);
		User user1 = salesRepository.getPhoneVerify(phone.getEmail());
		
		if(null == user1) {
			Map<Object, Object> model = new HashMap<>();
			model.put("message", "User not found");
		}

		userService.updateUser(user1, phone.getPassword());
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteUser(@RequestBody User user) {
		LOG.info("user." + user);
		salesRepository.deleteUser(user);
		return true;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PATCH)
	@ResponseBody
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User updateUser(@RequestBody User user) {
		LOG.info("user." + user);
		return salesRepository.updateUser(user);
	}

	@RequestMapping(value = "/updateUserVideoRestc/{id}/{age}", method = RequestMethod.PATCH)
	@ResponseBody
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User updateUserVideoRestc(@PathVariable int id, @PathVariable int age) {

		return salesRepository.updateUserAgeRestc(id, age);
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<User> getAllUser() {
		LOG.info("user.");
		return salesRepository.getAllUser();
	}


	@RequestMapping(value = "/subscribeFromRazorpay", method = RequestMethod.POST)
	public User subscribeFromRazorpay(@RequestBody PaymentCapture pameCapture) throws Exception {
		LOG.info("Saving user." + pameCapture.getPaymentid());
		RazorpayClient razorpay = new RazorpayClient(pameCapture.getApikey(), pameCapture.getSecKey());
		Payment payment = null;
		User user = null;
		try {
			JSONObject captureRequest = new JSONObject();
			captureRequest.put("amount", pameCapture.getAmount());
			captureRequest.put("currency", pameCapture.getCur());

			payment = razorpay.Payments.capture(pameCapture.getPaymentid(), captureRequest);
			LOG.info("Transction user." + pameCapture.getUserId());
			user = salesRepository.getSubUser(pameCapture.getUserId());
			LOG.info("Transction user." + pameCapture.toString() + "user " + user.toString());
			UserType ut = UserType.ONEMONTH;
			LOG.info("Transction user." + pameCapture.getAmount());
			if (pameCapture.getAmount() == 100) {
				ut = UserType.ONEMONTH;
			} else if (pameCapture.getAmount() == 9900) {
				ut = UserType.ONEMONTH;
			} else if (pameCapture.getAmount() == 14900) {
				ut = UserType.THREEMONTHS;
			} else if (pameCapture.getAmount() == 24900) {
				ut = UserType.SIXMONTHS;
			} else if (pameCapture.getAmount() == 39900) {
				ut = UserType.ONEYEAR;
			}
			com.gamotrance.OTT.Model.Payment p = new com.gamotrance.OTT.Model.Payment(user.getId(),
					String.valueOf(pameCapture.getAmount() / 100), ut.name(), LocalDate.now(), LocalDate.now());
			user.setUserType(ut);
			LocalDate ld = LocalDate.now().plusDays(new OttUtil().getValue(ut));
			user.setExpdate(ld.toString());
			user.setPayments(p);
			return salesRepository.updateUserWithId(user);
		} catch (RazorpayException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	@RequestMapping(value = "/generateChecksum", method = RequestMethod.POST)
	@ResponseBody
	public Checksum generateChecksum(@RequestBody Checksum checksum) {
		LOG.info("get realted users.");
		TreeMap<String, String> paramMap = new TreeMap<String, String>();
		paramMap.put("MID", MID);
		paramMap.put("ORDER_ID", checksum.getOrderId());
		paramMap.put("CUST_ID", checksum.getCUST_ID());
		paramMap.put("INDUSTRY_TYPE_ID", checksum.getINDUSTRY_TYPE_ID());
		paramMap.put("CHANNEL_ID", checksum.getCHANNEL_ID());
		paramMap.put("TXN_AMOUNT", checksum.getTXN_AMOUNT());
		paramMap.put("WEBSITE", checksum.getWEBSITE());
		paramMap.put("CALLBACK_URL", checksum.getCALLBACK_URL());
		String amt;

		String checkSum1 = "";

		Gson gson = new Gson();
		try {
			checkSum1 = PaytmChecksum.generateSignature(paramMap, MercahntKey);

		} catch (Exception e) {

		}
		return new Checksum(checksum.getMID(), checksum.getOrderId(), checksum.getCUST_ID(),
				checksum.getINDUSTRY_TYPE_ID(), checksum.getCHANNEL_ID(), checksum.getTXN_AMOUNT(),
				checksum.getWEBSITE(), checksum.getCALLBACK_URL(), checkSum1);

	}

	@RequestMapping(value = "/validateChecksum", method = RequestMethod.POST)
	@ResponseBody
	public Checksum validateChecksum(@RequestBody Checksum checksum1) throws Exception {

		JSONObject paytmParams = new JSONObject();
		String val = "[{\"mode\":\"BALANCE\",\"channels\":[]},{\"mode\":\"PPBL\",\"channels\":[]},{\"mode\":\"UPI\",\"channels\":[“UPI”,\"UPIPUSH\",\"UPIPUSHEXPRESS\"]},{\"mode\":“CREDIT_CARD\",\"channels\":[“VISA”,\"MASTER\",\"AMEX\"]},{\"mode\":“DEBIT_CARD\",\"channels\":[“VISA”,\"MASTER\",\"AMEX\"]},{\"mode\":“NET_BANKING\",\"channels\":[]},{\"mode\":\"EMI\",\"channels\":[“VISA”,\"MASTER\",\"AMEX\"]},{\"mode\":“PAYTM_DIGITAL_CREDIT”,\"channels\":[]}]";

		JSONObject body = new JSONObject();
		body.put("requestType", "Payment");
		body.put("mid", MID);
		body.put("websiteName", checksum1.getWEBSITE());
		body.put("orderId", checksum1.getOrderId());
		body.put("callbackUrl", checksum1.getCALLBACK_URL());
		JSONObject txnAmount = new JSONObject();
		txnAmount.put("value", checksum1.getTXN_AMOUNT());
		txnAmount.put("currency", "INR");

		JSONObject userInfo = new JSONObject();
		userInfo.put("custId", checksum1.getCUST_ID());
		body.put("txnAmount", txnAmount);
		body.put("userInfo", userInfo);

		JSONArray elist = new JSONArray();
		JSONObject m1 = new JSONObject();
		JSONArray value = new JSONArray();
		for (int i = 0; i <= 7; i++) {

			value = new JSONArray();
			m1 = new JSONObject();
			if (i == 0) {
				m1.put("mode", "BALANCE");
				m1.put("channels", value);
			} else if (i == 1) {
				m1.put("mode", "PPBL");
				m1.put("channels", value);
			} else if (i == 2) {
				value.put("UPI");
				value.put("UPIPUSH");
				value.put("UPIPUSHEXPRESS");
				m1.put("mode", "UPI");
				m1.put("channels", value);
			} else if (i == 3) {
				value.put("VISA");
				value.put("MASTER");
				value.put("AMEX");
				m1.put("mode", "CREDIT_CARD");
				m1.put("channels", value);
			} else if (i == 4) {
				value.put("VISA");
				value.put("MASTER");
				value.put("AMEX");
				m1.put("mode", "DEBIT_CARD");
				m1.put("channels", value);
			} else if (i == 5) {
				m1.put("mode", "NET_BANKING");
				m1.put("channels", value);
			} else if (i == 6) {
				value.put("VISA");
				value.put("MASTER");
				value.put("AMEX");
				m1.put("mode", "EMI");
				m1.put("channels", value);
			} else if (i == 7) {
				m1.put("mode", "PAYTM_DIGITAL_CREDIT");
				m1.put("channels", value);
			}

			elist.put(m1);
		}
		body.put("enablePaymentMode", elist);
		String checksum = PaytmChecksum.generateSignature(body.toString(), MercahntKey);
		JSONObject head = new JSONObject();
		head.put("signature", checksum);
		System.out.println("body" + body);
		System.out.println("head" + head);
		paytmParams.put("body", body);
		paytmParams.put("head", head);

		String post_data = paytmParams.toString();
		// for Staging
		URL url = new URL("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=" + MID + "&orderId="
				+ checksum1.getOrderId() + "");
		String response = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
			requestWriter.writeBytes(post_data);
			requestWriter.close();
			String responseData = "";
			InputStream is = connection.getInputStream();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));

			if ((responseData = responseReader.readLine()) != null) {
				System.out.append("Response: " + responseData);
			}
			responseReader.close();
			response = responseData;

			JSONObject jboJsonObject = new JSONObject(responseData);
			JSONObject resultInfo = (JSONObject) jboJsonObject.get("body");

			ObjectMapper m = new ObjectMapper();
			ResultInfo products = m.readValue(resultInfo.getJSONObject("resultInfo").toString().getBytes(),
					ResultInfo.class);
			System.out.println("Status:" + products.getResultStatus() + " Msg:" + products.getResultMsg());

			if (products.getResultStatus().equalsIgnoreCase("S")) {
				String value1 = resultInfo.getString("txnToken");
				return new Checksum(checksum1.getMID(), checksum1.getOrderId(), checksum1.getCUST_ID(),
						checksum1.getINDUSTRY_TYPE_ID(), checksum1.getCHANNEL_ID(), checksum1.getTXN_AMOUNT(),
						checksum1.getWEBSITE(), checksum1.getCALLBACK_URL(), value1); // txnToken

			} else if (products.getResultStatus().equalsIgnoreCase("F")) {
				return new Checksum(checksum1.getMID(), checksum1.getOrderId(), checksum1.getCUST_ID(),
						checksum1.getINDUSTRY_TYPE_ID(), checksum1.getCHANNEL_ID(), checksum1.getTXN_AMOUNT(),
						checksum1.getWEBSITE(), checksum1.getCALLBACK_URL(), null);
			} else {
				return new Checksum(checksum1.getMID(), checksum1.getOrderId(), checksum1.getCUST_ID(),
						checksum1.getINDUSTRY_TYPE_ID(), checksum1.getCHANNEL_ID(), checksum1.getTXN_AMOUNT(),
						checksum1.getWEBSITE(), checksum1.getCALLBACK_URL(), null);
			}

		} catch (Exception exception) {

			exception.printStackTrace();
			return new Checksum(checksum1.getMID(), checksum1.getOrderId(), checksum1.getCUST_ID(),
					checksum1.getINDUSTRY_TYPE_ID(), checksum1.getCHANNEL_ID(), checksum1.getTXN_AMOUNT(),
					checksum1.getWEBSITE(), checksum1.getCALLBACK_URL(), null);
		}
	}

	@RequestMapping(value = "/subscribeFromPaytm", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User subscribeFromPaytm(@RequestBody Transcation transcation) {
		LOG.info("Transction user : " + transcation.toString());
		User user = salesRepository.getSubUser(Integer.valueOf(transcation.getUserId()));
		LOG.info("Transction user." + transcation.toString() + "user " + user.toString());
		UserType ut = UserType.ONEMONTH;
		LOG.info("Transction user." + transcation.getTXNAMOUNT());
		if (plServices.getPlanAll() != null) {
			for (Plan p : plServices.getPlanAll()) {
				if (p.getPlanPrice().equalsIgnoreCase(transcation.getTXNAMOUNT())) {
					ut = p.getPlanType();
				}
			}
		}
		com.gamotrance.OTT.Model.Payment p = new com.gamotrance.OTT.Model.Payment(user.getId(),
				transcation.getTXNAMOUNT(), ut.name(), LocalDate.now(), LocalDate.now());
		user.setUserType(ut);
		LocalDate ld = LocalDate.now().plusDays(new OttUtil().getValue(ut));
		user.setExpdate(ld.toString());
		user.setPayments(p);
		transctionSErvices.addTranction(transcation);
		System.out.println("out pass");
		return salesRepository.updateUserWithId(user);
	}
	
	public UserType cut(String st) {
		UserType ut;
		switch (st) {
		case "plan_one_month":
			ut = UserType.ONEMONTH;
			break;
		case "plan_three_month":
			ut = UserType.THREEMONTHS;
			break;
		case "plan_six_month":
			ut = UserType.SIXMONTHS;
			break;

		default:
			ut = UserType.BASIC;
			break;
		}
		return ut;
	}

}
