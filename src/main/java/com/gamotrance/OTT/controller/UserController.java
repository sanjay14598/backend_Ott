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
	private static String MID = "GnqWZs60135668160054";
	private static String MercahntKey = "rr82qw5TMLHKd@Zw";
	//private static String MID = "mBSVRT27943668214901";
	//private static String MercahntKey = "AL5eo2xV_yOwMLIf";
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

	public UserController(PlanServices plServices, OtpServices otpServices, CustomUserDetailsService userService,
			UserServicesImplimentation salesRepository, TransctionSErvices transctionSErvices,
			FileStorageService fileStorageService) {
		this.salesRepository = salesRepository;
		this.userService = userService;
		this.otpServices = otpServices;
		this.plServices = plServices;
		this.fileStorageService = fileStorageService;
		this.transctionSErvices = transctionSErvices;
	}

	@ApiOperation(value = "Make a GET request to upload the file", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found") })
	@RequestMapping(value = "/getValidateUser/{name}/{password}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User getValidateUser(@PathVariable String name, @PathVariable String password) {
		LOG.info("Getting all users.");
		return salesRepository.getValidate(name, password);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found") })
	@RequestMapping(value = "/getValidateUserWithDevice/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getValidateUserWithDevice(@RequestBody LoginCont device, UriComponentsBuilder ucBuilder) {
		LOG.info("Getting all users." + device.getUserName() + "" + device.getPassword() + " \n" + device);
		// return salesRepository.getValidate(name, password);

		User user = salesRepository.getValidate(device.getUserName(), device.getPassword());
		if (user != null) {
			if (user.getDevices() == null) {
				List<Device> devices = new ArrayList<Device>();
				devices.add(device.getDevice());
				user.setDevices(devices);
				salesRepository.updateUser(user);
				return new ResponseEntity<User>(salesRepository.getValidate(device.getUserName(), device.getPassword()),
						HttpStatus.ACCEPTED);
			}

			else if (user.getUserType().equals(UserType.SIXMONTHS) || user.getUserType().equals(UserType.ONEYEAR)
					|| user.getUserType().equals(UserType.ONEMONTH)
					|| user.getUserType().equals(UserType.THREEMONTHS)) {
				if (user.getDevices().size() >= 1) {
					return new ResponseEntity(
							new CustomErrorType("This Device is Login with Device" + user.getDevices().get(0).getBrand()
									+ " Device Name" + user.getDevices().get(0).getDeviceName()),
							HttpStatus.CONFLICT);
				} else {
					List<Device> devices = user.getDevices();
					devices.add(device.getDevice());
					user.setDevices(devices);
					salesRepository.updateUser(user);
					return new ResponseEntity<User>(
							salesRepository.getValidate(device.getUserName(), device.getPassword()),
							HttpStatus.ACCEPTED);
				}
			}

		} else {
			return new ResponseEntity(new CustomErrorType("We Don't Found any user with this account  "),
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(salesRepository.getValidate(device.getUserName(), device.getPassword()),
				HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/checkUserIfExist/{email}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean checkUserIfExist(@PathVariable String email) {
		LOG.info("Getting all users.");
		return salesRepository.checkIfUserExist(email);
	}

	@RequestMapping(value = "/getPaymentOrder", method = RequestMethod.POST)
	public User getPaymentOrder(@RequestBody PaymentCapture pameCapture) throws Exception {
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

	@RequestMapping(value = "/createUserByPhone", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> createUserByPhone(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		if (salesRepository.getPhoneExist(user.getPhone())) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A User with name " + user.getPhone() + " already exist."),
					HttpStatus.CONFLICT);
		}
		salesRepository.createAccount(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/getUserById/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<User>(salesRepository.createAccount(user), HttpStatus.CREATED);
	}

	@PostMapping("/createUserByEmail")
	public ResponseEntity<?> createUserByEmail(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		try {
			Optional<User> user1 = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));
			if (user1.isPresent()) {
				return new ResponseEntity(
						new CustomErrorType("Unable to create  this " + user.getEmail() + " already exist."),
						HttpStatus.CONFLICT);
			}
			salesRepository.createAccount(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/getUserById/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<>(salesRepository.createAccount(user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/addUserFromExcel", method = RequestMethod.POST)
	public User addUserFromExcel(@RequestBody User user) {
		LOG.info("Saving user." + user.getEmail());
		return salesRepository.createAccount(user);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteUser(@RequestBody User user) {
		LOG.info("user." + user);
		salesRepository.deleteUser(user);
		return true;
	}

	@RequestMapping(value = "/getUser/{email}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User getUser(@PathVariable String email) {
		LOG.info("user." + email);
		return salesRepository.getUser(email);
	}

	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		Optional<User> user = Optional.ofNullable(userRepo.findByEmail(email));
		if (user.isPresent()) {
			return new ResponseEntity<>(userRepo.findByEmail(email), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
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

	@RequestMapping(value = "/getUserByID/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User getSubUser(@PathVariable int id) {
		LOG.info("get realted users." + id);
		return salesRepository.getSubUser(id);
	}

	@RequestMapping(value = "/getPasswordCheeck", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity getPasswordCheeck(@RequestBody AuthBody ab) {
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

	@RequestMapping(value = "/getUserByuserType/{type}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<User> getUserByuserType(@PathVariable String type) {
		LOG.info("get realted users." + type);
		return salesRepository.getUserByuserType(type);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUserByPhone/{phone}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity getUserByPhone(@PathVariable String phone) {
		LOG.info("get phone Verify users." + phone);
		// boolean isAuthenticated = BCrypt.checkpw(candidatePassword, passwordHash);
		Map<Object, Object> model = new HashMap<>();
		model.put("User", salesRepository.getPhoneVerify(phone));
		return ok(model);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getPhoneVerify", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity getPhoneVerify(@RequestBody AuthBody phone) {
		LOG.info("get phone Verify users." + phone);
		// boolean isAuthenticated = BCrypt.checkpw(candidatePassword, passwordHash);
		Map<Object, Object> model = new HashMap<>();
		boolean b = salesRepository.checkIfUserExist(phone.getEmail());
		if (b) {
			User u = salesRepository.getPhoneVerify(phone.getEmail());
			model.put("user", u);
			model.put("exist", b);
			model.put("ResponseCode", 20);
		} else {
			model.put("user", null);
			model.put("exist", b);
			model.put("ResponseCode", 209);
		}
		return ok(model);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUseerPhone/{phone}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity getUseerPhone(@PathVariable String phone) {
		LOG.info("get phone Verify users." + phone);
		// boolean isAuthenticated = BCrypt.checkpw(candidatePassword, passwordHash);
		Map<Object, Object> model = new HashMap<>();
		boolean b = salesRepository.checkIfUserExist(phone);
		if (b) {
			User u = salesRepository.getPhoneVerify(phone);
			model.put("user", u);
			model.put("exist", b);
			model.put("ResponseCode", 20);
		} else {
			model.put("user", null);
			model.put("exist", b);
			model.put("ResponseCode", 209);
		}
		return ok(model);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity updatePassword(@RequestBody AuthBody phone) {
		LOG.info("get phone Verify users." + phone);
		User user1 = salesRepository.getPhoneVerify(phone.getEmail());

		userService.updateUser(user1, phone.getPassword());
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}

	@RequestMapping(value = "/UserOtpVerification/{email}/{otp}/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User UserOtpVerification(@PathVariable String email, @PathVariable String otp) {
		LOG.info("get realted users." + email + " the otp is:" + otp);
		return salesRepository.getUserOtpVerification(email, otp);
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
				return new ResponseEntity(new CustomErrorType("Worng Otp !!!"), HttpStatus.CONFLICT);
			}
		} else {
			return new ResponseEntity<User>(
					salesRepository.createAccount(
							new User(otp.getMobile().hashCode(), "+91" + otp.getMobile(), UserType.BASIC)),
					HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/getAllOtp", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<Otp> getAllOtp() {
		return otpServices.getOtpList();

	}

	@RequestMapping(value = "/getchechsum", method = RequestMethod.POST)
	@ResponseBody
	public Checksum getChecksum(@RequestBody Checksum checksum) {
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

	@RequestMapping(value = "/getchechsumtest", method = RequestMethod.POST)
	@ResponseBody
	public Checksum getChecksumtest(@RequestBody Checksum checksum1) throws Exception {

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

	@RequestMapping(value = "/addTransction", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public User addTransction(@RequestBody Transcation transcation) {
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

	@RequestMapping(value = "/deleteTransction", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String deleteTransction(@RequestBody Transcation transcation) {
		LOG.info("user." + transcation.toString());
		transctionSErvices.deleteTranction(transcation);
		return null;
	}

	@RequestMapping(value = "/getTransctionById/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public Transcation getTransction(@PathVariable String id) {
		LOG.info("user." + id);
		return transctionSErvices.getTranctionById(id);
	}

	@RequestMapping(value = "/getAllTransction/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<Transcation> getAllTransction() {
		LOG.info("user.");
		return transctionSErvices.getAllTranction();
	}

	@RequestMapping(value = "/getUserbyPayments/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<User> getUserbyPayments() {
		LOG.info("user.");
		return salesRepository.getAllUserbypaymentsRep();
	}

	@RequestMapping(value = "/deleteAllUser", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteTransction() {
		// LOG.info( "user."+transcation.getId());

		return salesRepository.deleteAllUser();
	}

	@RequestMapping(value = "/getUserByDate/{days}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<User> getUserByDate(@PathVariable long days) {
		// LOG.info( "user."+transcation.getId());

		return salesRepository.getUserByDate(LocalDate.now().minusDays(days));
	}

	@RequestMapping(value = "/getUserByDateSubscription/{days}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<User> getUserByDateSubscription(@PathVariable long days) {
		// LOG.info( "user."+transcation.getId());

		return salesRepository.getUserByDateSubscription(LocalDate.now().minusDays(days));
	}

	@RequestMapping(value = "/getSync", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean getSync() {
		// LOG.info( "user."+transcation.getId());

		return salesRepository.deleteAllUser();
	}

	@RequestMapping(value = "/deleteDevice/{deviceId}/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> deleteDevice(@PathVariable int deviceId, @PathVariable int id,
			UriComponentsBuilder ucBuilder) {
		// LOG.info( "user."+transcation.getId());
		User user = salesRepository.getSubUser(id);
		if (user != null) {
			if (user.getDevices() != null) {
				List<Device> dev = new ArrayList<Device>();
				for (Device d : user.getDevices()) {
					if (deviceId == d.getId()) {

					} else {
						dev.add(d);
					}
				}
				user.setDevices(dev);
			}
		}
		return new ResponseEntity<User>(salesRepository.updateUser(user), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/SyncAllUser", method = RequestMethod.PATCH)
	public boolean SyncAllUser(@RequestBody Genre url) {
		// LOG.info( "user."+transcation.getId());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url.getGenreName(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> pojoObjList = responseEntity.getBody();
		System.out.println(pojoObjList.size());
		salesRepository.syncAll(pojoObjList);
		return true;
		// return salesRepository.deleteAllUser();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity login(@RequestBody AuthBody data) {
		try {

			String username = data.getEmail();
			User u = salesRepository.getUserByPhone(username);
			if (u.getPassword().equalsIgnoreCase("key")) {
				Map<Object, Object> model = new HashMap<>();
				model.put("rescode", 208);
				model.put("susmsg", "Please Update Password");
				return ok(model);
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (!encoder.matches(data.getPassword(), u.getPassword())) {
				Map<Object, Object> model = new HashMap<>();
				model.put("rescode", 209);
				model.put("susmsg", "Password Not Match");
				return ok(model);
			} else if (u == null) {
				Map<Object, Object> model = new HashMap<>();
				model.put("rescode", 209);
				model.put("susmsg", "Username Not Found");
			}
			System.out.println(username + "   " + data.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			String token = jwtTokenProvider.createToken(username, userService.findUserByPhone(username).getRoles());
			Map<Object, Object> model = new HashMap<>();
			System.out.println(token);

			model.put("user", salesRepository.getUserByPhone(username));
			model.put("rescode", 200);
			model.put("susmsg", "OK");
			model.put("token", token);
			System.out.println(model.toString());
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid email/password supplied");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity register(@RequestBody User user) {
		User userExists = userService.findUserByPhone(user.getPhone());
		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + user.getPhone() + " already exists");
		}
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}

	@RequestMapping(value = "/syncAllDataFromMysql", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean syncAllDataFromMysql() {

		String id = "", name = "", phone = "", email = "", subdate = "", gender = "", age = "", date = "";

		try {

			FileReader filereader = new FileReader("/OTT/user.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();
			List<User> users = new ArrayList<>();
			// print Data
			for (String[] row : allData) {
				int i = 0;
				for (String cell : row) {
					if (i == 0) {
						id = cell;
					} else if (i == 2) {
						age = cell;
					} else if (i == 3) {
						name = cell;
					} else if (i == 4) {
						email = cell;
					} else if (i == 5) {
						phone = cell;
					}
					System.out.print(cell + "\t");
					i++;
				}

				if (age.isEmpty()) {
					users.add(new User(Integer.valueOf(id), name, email, 18, null, "key", "+91" + phone, null, null,
							LocalDate.now(), LocalDate.now(), LocalDate.now(), null, null));

				} else {
					users.add(new User(Integer.valueOf(id), name, email, 18, null, "key", "+91" + phone, null, null,
							LocalDate.now(), LocalDate.now(), LocalDate.now(), null, null));

				}

				System.out.println();
			}

			System.out.println(users.size());
			FileReader filereader1 = new FileReader("/OTT/subscription.csv");
			CSVReader csvReader1 = new CSVReaderBuilder(filereader1).withSkipLines(1).build();
			List<String[]> allData1 = csvReader1.readAll();
			List<sub> users1 = new ArrayList<>();
			UserType ut;
			String subType = "", userid = "", amount = "", fromdate = "", expdate = "";
			for (String[] row1 : allData1) {
				int i = 0;
				for (String cell1 : row1) {
					if (i == 1) {
						subType = cell1;
					} else if (i == 2) {
						userid = cell1;
					} else if (i == 4) {
						amount = cell1;
					} else if (i == 5) {
						fromdate = cell1;
					} else if (i == 6) {
						expdate = cell1;
					}
					System.out.print(cell1 + "\t");
					i++;
				}
				users1.add(new sub(userid, fromdate, amount, expdate, subType));
				System.out.println();
			}
			List<sub> lst = new ArrayList<>();
			Map<String, sub> lmap = new HashMap<>();
			for (sub s : users1) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = new Date(Long.valueOf(s.getDateTo()) * 1000);
				if (date1.after(new Date())) {
					lmap.put(s.getId(), s);
					lst.add(s);
					System.out.println(format.format(date1));
				}
			}
			List<User> userList = new ArrayList<>();
			Date date1 = null;
			Date date2 = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Role userRole = salesRepository.getRole("USER");
			for (User u : users) {
				int flag = 0;
				for (int i = 0; i < lst.size(); i++) {
					if (u.getId() == Integer.valueOf(lst.get(i).getId())) {
						sub s = lst.get(i);
						date1 = new Date(Long.valueOf(s.getDateTo()) * 1000);
						date2 = new Date(Long.valueOf(s.getDatefrom()) * 1000);
						u.setPayments(
								new com.gamotrance.OTT.Model.Payment(u.getId(), s.getAmount(), cut(s.getType()).value,
										LocalDate.parse(format.format(date2)), LocalDate.parse(format.format(date2))));

						u.setUserType(cut(s.getType()));
						u.setRoles(new HashSet<>(Arrays.asList(userRole)));
						u.setSuscriptionDate(LocalDate.parse(format.format(date2)));
						u.setExpdate(format.format(date1));
						userList.add(u);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					u.setRoles(new HashSet<>(Arrays.asList(userRole)));
					u.setUserType(UserType.BASIC);
					userList.add(u);
				}

				System.out.println(u.toString());
			}
			System.out.println(userList.size());
			salesRepository.saveAll(userList);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
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
