package com.gamotrance.OTT.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.gamotrance.OTT.CustomErrorType;
import com.gamotrance.OTT.Model.CatagoryResponse;
import com.gamotrance.OTT.Model.Cataogry;
import com.gamotrance.OTT.Model.ContVideo;
import com.gamotrance.OTT.Model.CountryVideoDecoded;
import com.gamotrance.OTT.Model.CountryWrapper;
import com.gamotrance.OTT.Model.DashResponse;
import com.gamotrance.OTT.Model.DisLike;
import com.gamotrance.OTT.Model.Favourite;
import com.gamotrance.OTT.Model.Genre;
import com.gamotrance.OTT.Model.Likes;
import com.gamotrance.OTT.Model.Note;
import com.gamotrance.OTT.Model.PeerType;
import com.gamotrance.OTT.Model.Response;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.Thumb;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserView;
import com.gamotrance.OTT.Model.VideoCatList;
import com.gamotrance.OTT.Model.VideoResponse;
import com.gamotrance.OTT.Model.VideoType;
import com.gamotrance.OTT.Model.WatchList;
import com.gamotrance.OTT.services.CatogarryServices;
import com.gamotrance.OTT.services.ContVideoServices;
import com.gamotrance.OTT.services.CountryVideoDecoedServices;
import com.gamotrance.OTT.services.DisLikeSevices;
import com.gamotrance.OTT.services.FCMService;
import com.gamotrance.OTT.services.FavouriteServices;
import com.gamotrance.OTT.services.FileStorageService;
import com.gamotrance.OTT.services.LikesServices;
import com.gamotrance.OTT.services.PushNotificationService;
import com.gamotrance.OTT.services.RemindMeServices;
import com.gamotrance.OTT.services.SingleVideoServicesImplimentation;
import com.gamotrance.OTT.services.UserViewServices;
import com.gamotrance.OTT.services.VideoCatListServices;
import com.gamotrance.OTT.services.WatchListServices;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/Video")
public class VideoController {

	private static final String UPLOADED_FOLDER = "/home/gulshan/Downloads/Isociety/";
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final SingleVideoServicesImplimentation salesRepository;
	private final String TOPIC = "JavaSampleApproach";

	@Autowired
	PushNotificationService androidPushNotificationsService;

	@Value("${file.upload-dir}")
	private String filRootDir;

	private FileStorageService fileStorageService;
	private UserViewServices userViewServices;
	private LikesServices likesServices;
	private DisLikeSevices disLikeSevices;
	private CatogarryServices catServices;
	private RemindMeServices remindMeServices;
	private ContVideoServices contVideoServices;
	private VideoCatListServices videoCatListServices;
	private final FCMService firebaseService;
	private WatchListServices watchListServices;
	private final FavouriteServices favouriteServices;

	public VideoController(VideoCatListServices videoCatListServices, DisLikeSevices disLikeSevices,
			FavouriteServices favouriteServices, WatchListServices watchListServices, CatogarryServices catServices,
			FCMService firebaseService, SingleVideoServicesImplimentation salesRepository,
			FileStorageService fileStorageService, UserViewServices userViewServices, LikesServices likesServices,
			RemindMeServices remindMeServices, ContVideoServices contVideoServices) {

		this.videoCatListServices = videoCatListServices;
		this.firebaseService = firebaseService;
		this.disLikeSevices = disLikeSevices;
		this.salesRepository = salesRepository;
		this.fileStorageService = fileStorageService;
		this.favouriteServices = favouriteServices;
		this.userViewServices = userViewServices;
		this.likesServices = likesServices;
		this.remindMeServices = remindMeServices;
		this.contVideoServices = contVideoServices;
		this.catServices = catServices;
		this.watchListServices = watchListServices;

	}

//  	@RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
//  	public ResponseEntity send() throws JSONException {
//
//  		JSONObject body = new JSONObject();
//  		body.put("to", "/topics/" + TOPIC);
//  		body.put("priority", "high");
//
//  		JSONObject notification = new JSONObject();
//  		notification.put("title", "JSA Notification");
//  		notification.put("body", "Happy Message!");
//  		
//  		JSONObject data = new JSONObject();
//  		data.put("Key-1", "JSA Data 1");
//  		data.put("Key-2", "JSA Data 2");
//
//  		body.put("notification", notification);
//  		body.put("data", data);
//
//  /**
//  		{
//  		   "notification": {
//  		      "title": "JSA Notification",
//  		      "body": "Happy Message!"
//  		   },
//  		   "data": {
//  		      "Key-1": "JSA Data 1",
//  		      "Key-2": "JSA Data 2"
//  		   },
//  		   "to": "/topics/JavaSampleApproach",
//  		   "priority": "high"
//  		}
//  */
//
//  		HttpEntity request = new HttpEntity<>(body.toString());
//
//  		CompletableFuture pushNotification = PushNotificationService.send(request);
//  		CompletableFuture.allOf(pushNotification).join();
//
//  		try {
//  			String firebaseResponse = pushNotification.get();
//  			
//  			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//  		} catch (InterruptedException e) {
//  			e.printStackTrace();
//  		} catch (ExecutionException e) {
//  			e.printStackTrace();
//  		}
//
//  		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
//  	}
//      @RequestMapping(value="/send-notification",method = RequestMethod.POST)
//      @ResponseBody
//      public String sendNotification(@RequestBody Note note,
//                                     @RequestParam String topic) throws FirebaseMessagingException {
//          return firebaseService.sendNotification(note, topic);
//      }
//      
	// @Cacheable(value = "getAllSingleVideo")
	@RequestMapping(value = "/getAllVideo", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<SingleVideo> getAllSingleVideo() {
		return salesRepository.getAllSingleVideo();
	}

//      @RequestMapping(value = "/getAllVideoByCountry", method = RequestMethod.GET)
//  	public List<SingleVideo> getAllSingleVideo(String country){
//    	  List<SingleVideo> list=new ArrayList<SingleVideo>();
//    	  for(int id:countryVideoDecoedServices.getHomePagerContainer(country))
//    	  {
//    		  list.add(salesRepository.getVideoById(id));
//    	  }
//  		return list;
//  	}
	@Cacheable(value = "singlevideo", key = "#id", unless = "#result.likes < 500")
	@RequestMapping(value = "/getVideoById/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public SingleVideo getVideoById(@PathVariable int id) {
		return salesRepository.getVideoById(id);
	}

	@Cacheable(value = "singlevideo", key = "#name", unless = "#result.likes < 500")
	@RequestMapping(value = "/getVideoByName/{name}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public SingleVideo getSingleVideoByName(@PathVariable String name) {
		return salesRepository.getSingleVideoByName(name);
	}

	@RequestMapping(value = "/getVideoByGenre/{genreName}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<SingleVideo> getSingleVideoByGenre(@PathVariable String genreName) {
		return salesRepository.getSingleVideoByGenre(genreName);
	}

	@RequestMapping(value = "/addVideo/", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public SingleVideo addSingleVideo(@RequestBody SingleVideo singleVideo) {
		SingleVideo sl = salesRepository.addSingleVideo(singleVideo);
		try {
			System.out.println("the video sort1");
			List<CatagoryResponse> list = new ArrayList<>();
			for (Cataogry c : catServices.getAllCataogry()) {
				System.out.println("the video sort2");
				List<DashResponse> dlist = new ArrayList<>();
				System.out.println("the video sort4" + c.getPeer().toString());
				List<SingleVideo> slist = salesRepository.getVideoByCatogary(c.getPeer().toString());
				System.out.println("the video sort4" + slist.size());
				System.out.println("return data: " + salesRepository.getVideoByCatogary(c.getPeer().toString())
						+ " list size" + slist.size());
				if (slist != null) {
					if (slist.size() > 0) {

						for (SingleVideo s : slist) {
							dlist.add(new DashResponse(s.getId(), s.getThumbs(), s.getTitle(), s.getVideoType(),
									s.getmThumbs()));
						}

						if (dlist != null) {
							Collections.reverse(dlist);
							list.add(new CatagoryResponse(c.getPeer().toString(), c.getBoxType(), dlist));
						}

					}
				}
			}
			System.out.println("the video sort 3");
			VideoCatList vl = new VideoCatList(0, list, "OK", "200");
			VideoCatList vl1 = videoCatListServices.addDash(vl);
			System.out.println(vl1.toString());
			flushall("redis-cli flushall");
		} catch (Exception e) {
			System.out.println("the video sort");
			e.printStackTrace();
		}
		return sl;
	}

	@CachePut(value = "singlevideo", key = "#singleVideo.id")
	@RequestMapping(value = "/updateVideo/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public SingleVideo updateSingleVideo(@RequestBody SingleVideo singleVideo) {
		SingleVideo sl = salesRepository.updateSingleVideo(singleVideo);
		try {
			flushall("redis-cli flushall");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sl;
	}

	@Cacheable(value = "getAllSingleVideo")
	@RequestMapping(value = "/deleteAllVideo", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteAllVideo() {
		boolean b = salesRepository.deleteAllVideo();

		try {
			flushall("redis-cli flushall");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	@CacheEvict(value = "singlevideo")
	@RequestMapping(value = "/deleteVideo/", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean deleteSingleVideo(@RequestBody SingleVideo singleVideo) {
		return salesRepository.deleteSingleVideo(singleVideo);
	}

	@RequestMapping(value = "/getVideoByChannel/{id}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<SingleVideo> getVideoByChannel(@PathVariable int id) {
		return salesRepository.getVideoByChannel(id);
	}

	@RequestMapping(value = "/getVideoByCountry/{countryName}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public List<SingleVideo> getVideoByCountry(@PathVariable String countryName) {
		return salesRepository.getVideoByCountry(countryName);
	}

	@RequestMapping(value = "/addLikes/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addLikes(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		Likes lk = likesServices.getLikesExistance(userId, vodId);
		if (lk == null) {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			Long vi = singleVideo.getLikes();
			Long inc = vi + 1;
			Likes likes = new Likes();
			SingleVideo sv = new SingleVideo(singleVideo.getId(), singleVideo.getTitle(), singleVideo.getDescription(),
					inc, singleVideo.getChannelId(), singleVideo.getCasts(), singleVideo.getGenres(),
					singleVideo.getTralier(), singleVideo.getDirectors(), singleVideo.getEps(), singleVideo.getWriter(),
					inc, singleVideo.getVideoType(), singleVideo.getThumbs(), singleVideo.getPeerType(),
					singleVideo.getAgeGroup(), singleVideo.getPartNumber(), singleVideo.getVdoUrl(),
					singleVideo.getmThumbs(), singleVideo.getDuration(), singleVideo.getUpcommingDate(),
					singleVideo.getCountryList());
			likesServices
					.addLike(new Likes(LocalDate.now().toString().concat(userId.toString()).hashCode(), userId, vodId));
			SingleVideo sgVdo = salesRepository.updateSingleVideo(sv);

			return new ResponseEntity("your liked submited", HttpStatus.ACCEPTED);
		} else {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			return new ResponseEntity("your already liked this video", HttpStatus.ACCEPTED);
		}

	}

	@RequestMapping(value = "/removeLikes/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> removeLikes(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		Likes lk = likesServices.getLikesExistance(userId, vodId);
		if (lk == null) {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			return new ResponseEntity("your already liked this video", HttpStatus.ACCEPTED);
		} else {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			Long vi = singleVideo.getLikes();
			Long inc = vi - 1;
			Likes likes = new Likes();
			SingleVideo sv = new SingleVideo(singleVideo.getId(), singleVideo.getTitle(), singleVideo.getDescription(),
					inc, singleVideo.getChannelId(), singleVideo.getCasts(), singleVideo.getGenres(),
					singleVideo.getTralier(), singleVideo.getDirectors(), singleVideo.getEps(), singleVideo.getWriter(),
					inc, singleVideo.getVideoType(), singleVideo.getThumbs(), singleVideo.getPeerType(),
					singleVideo.getAgeGroup(), singleVideo.getPartNumber(), singleVideo.getVdoUrl(),
					singleVideo.getmThumbs(), singleVideo.getDuration(), singleVideo.getUpcommingDate(),
					singleVideo.getCountryList());
			likesServices.deleteLike(lk);
			SingleVideo sgVdo = salesRepository.updateSingleVideo(sv);

			return new ResponseEntity("your dislike submited", HttpStatus.ACCEPTED);

		}

	}

	@RequestMapping(value = "/addDisLikes/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addDisLikes(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		DisLike lk = disLikeSevices.getDisLikeExistance(userId, vodId);
		if (lk == null) {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			Long vi = singleVideo.getDisLike();
			Long inc = vi + 1;
			// Likes likes=new Likes();
			// singleVideo.setDisLike(inc);
			SingleVideo sv = new SingleVideo(singleVideo.getId(), singleVideo.getTitle(), singleVideo.getDescription(),
					singleVideo.getLikes(), inc, singleVideo.getChannelId(), singleVideo.getCasts(),
					singleVideo.getGenres(), singleVideo.getTralier(), singleVideo.getDirectors(), singleVideo.getEps(),
					singleVideo.getWriter(), singleVideo.getViews(), singleVideo.getVideoType(),
					singleVideo.getThumbs(), singleVideo.getPeerType(), singleVideo.getAgeGroup(),
					singleVideo.getPartNumber(), singleVideo.getVdoUrl(), singleVideo.getmThumbs(),
					singleVideo.getDuration(), singleVideo.getUpcommingDate(), singleVideo.getCountryList());
			disLikeSevices.addDisLike(
					new DisLike(LocalDate.now().toString().concat(userId.toString()).hashCode(), userId, vodId));
			SingleVideo sgVdo = salesRepository.updateSingleVideo(sv);

			return new ResponseEntity("your liked submited", HttpStatus.ACCEPTED);
		} else {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			return new ResponseEntity("your already liked this video", HttpStatus.ACCEPTED);
		}

	}

	@RequestMapping(value = "/removeDisLike/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> removeDisLike(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		DisLike lk = disLikeSevices.getDisLikeExistance(userId, vodId);
		if (lk == null) {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			return new ResponseEntity("your already liked this video", HttpStatus.ACCEPTED);
		} else {
			SingleVideo singleVideo = salesRepository.getVideoById(Integer.valueOf(vodId));
			Long vi = singleVideo.getDisLike();
			Long inc = vi - 1;
			singleVideo.setDisLike(inc);
			SingleVideo sv = new SingleVideo(singleVideo.getId(), singleVideo.getTitle(), singleVideo.getDescription(),
					singleVideo.getLikes(), inc, singleVideo.getChannelId(), singleVideo.getCasts(),
					singleVideo.getGenres(), singleVideo.getTralier(), singleVideo.getDirectors(), singleVideo.getEps(),
					singleVideo.getWriter(), singleVideo.getViews(), singleVideo.getVideoType(),
					singleVideo.getThumbs(), singleVideo.getPeerType(), singleVideo.getAgeGroup(),
					singleVideo.getPartNumber(), singleVideo.getVdoUrl(), singleVideo.getmThumbs(),
					singleVideo.getDuration(), singleVideo.getUpcommingDate(), singleVideo.getCountryList());
			disLikeSevices.deleteDisLike(lk);
			SingleVideo sgVdo = salesRepository.updateSingleVideo(sv);

			return new ResponseEntity("your dislike submited", HttpStatus.ACCEPTED);

		}

	}

	@RequestMapping(value = "/getLikes/{userId}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getLikes(@PathVariable String userId, UriComponentsBuilder ucBuilder) {
		// SingleVideo singleVideo=salesRepository.getVideoById(Integer.valueOf(vodId));
		return new ResponseEntity(likesServices.getLikesByUser(userId), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/addUserView/{userId}/{vdoId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public Integer addUserView(@PathVariable Integer userId, @PathVariable Integer vdoId) {
		Integer id = LocalDate.now().toString().concat(userId.toString()).hashCode();
		UserView userv = userViewServices
				.addUserView(new UserView(id, userId.toString(), vdoId.toString(), LocalDate.now(), LocalDate.now()));
		SingleVideo singleVideo = salesRepository.getVideoById(vdoId);
		Long vi = singleVideo.getViews();
		Long inc = vi + 1;

		SingleVideo sv = new SingleVideo(singleVideo.getId(), singleVideo.getTitle(), singleVideo.getDescription(),
				singleVideo.getLikes(), singleVideo.getChannelId(), singleVideo.getCasts(), singleVideo.getGenres(),
				singleVideo.getTralier(), singleVideo.getDirectors(), singleVideo.getEps(), singleVideo.getWriter(),
				inc, singleVideo.getVideoType(), singleVideo.getThumbs(), singleVideo.getPeerType(),
				singleVideo.getAgeGroup(), singleVideo.getPartNumber(), singleVideo.getVdoUrl(),
				singleVideo.getmThumbs(), singleVideo.getDuration(), singleVideo.getUpcommingDate(),
				singleVideo.getCountryList());

		SingleVideo sgVdo = salesRepository.updateSingleVideo(sv);
		return userv.getId();
	}

	@RequestMapping(value = "/updateUserView/{userId}", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public String updateUserView(@PathVariable Integer userId) {
		UserView userView2 = userViewServices.getUserViewById(userId);
		if (userView2 != null) {
			userView2.setEndTimeStamp(LocalDate.now());
			UserView userView = userViewServices.addUserView(userView2);
			String response = salesRepository.getVideoById(Integer.valueOf(userView2.getVideoId())).getViews()
					.toString();
			System.out.println("the response views on update " + response);
			return response;
		}
		return null;
	}

//    @RequestMapping(value = "/upload/{id}/", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://65.2.89.128:3000")
//    public Response uploadFile(@RequestParam("file") MultipartFile file,@PathVariable int id) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/home/gulshan/OttApp/OttProd/profileImages/")
//            .path(fileName)
//            .toUriString();
//        SingleVideo vdo=salesRepository.getVideoById(id);
//        //uri.replace("65.2.89.128","35.192.166.223")
//
//        vdo.setThumbs(fileDownloadUri.replace("65.2.89.128","35.192.166.223"));
//        System.out.println(vdo.getThumbs());
//        salesRepository.updateSingleVideo(vdo);
//        return new Response(fileName, fileDownloadUri,
//            file.getContentType(), file.getSize());
//    }

//    @RequestMapping(value = "/uploadMultipleFiles/{id}/", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://65.2.89.128:3000")
//    public List <Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable int id) {
//        return Arrays.asList(files)
//            .stream()
//            .map(file->uploadFile(file, id))
//            .collect(Collectors.toList());
//    }

	@RequestMapping(value = "/addRemindMe/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addRemindMe(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		if (remindMeServices.addRemindMe(userId, vodId)) {
			// logger.error("Unable to create. A User with name {} already exist",
			// user.getName());
			return new ResponseEntity(new CustomErrorType("User " + userId + " Unable to Add Remind To This Video "),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity("User " + userId + " Added Remind To This Video ", HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updateRemindMe/{vodId}/{userId}", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> updateRemindMe(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		if (remindMeServices.updateRemindMe(userId, vodId)) {
			// logger.error("Unable to create. A User with name {} already exist",
			// user.getName());
			return new ResponseEntity("User " + userId + " Deleted Video From Remind List ", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity(new CustomErrorType("Video " + vodId + " Not exist on List "),
					HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "/getRemindList/{userId}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getRemindList(@PathVariable String userId, UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(remindMeServices.getRemindList(userId), HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = "/getContVideoList/{userId}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getContVideoList(@PathVariable String userId, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(contVideoServices.getContVideoList(userId), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getVideoByVideoType/{vodType}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getVideoByVideoType(@PathVariable String vodType, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(salesRepository.getVideoByVodType(vodType), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/allDeleteContWatch", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> allDeleteContWatch(UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(contVideoServices.deleteAll(), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/updateDeleteVideoByUser/", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> updateDeleteVideoByUser(@RequestBody ContVideo contVideo, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(contVideoServices.updateVideoByUser(contVideo), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/updateSaveVideoByUser/{vodId}/{userId}", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> updateSaveVideoByUser(@PathVariable String vodId, @PathVariable String userId,
			UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(contVideoServices.updateVideoByUser(vodId, userId), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/addContWatching", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addContWatching(@RequestBody ContVideo contVideo, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());

		SingleVideo s = salesRepository.getVideoById(Integer.valueOf(contVideo.getVodId()));
		System.out.println("user " + s.toString());
		LOG.info("userData" + s.toString());
		ContVideo ct = null;
		Map<Object, Object> model = new HashMap<>();

		if (s != null) {

			if (s.getVideoType().equals(VideoType.WEBSERIES)) {
				if (s.getEps() != null) {
					ct = new ContVideo(contVideo.getId(), contVideo.getVodId(), contVideo.getLastTime(), s.getThumbs(),
							String.valueOf(s.getAgeGroup()), s.getEps().get(0).getVdoUrl(),
							s.getEps().get(0).getTitle(), s.getmThumbs(), contVideo.getDuration(),
							contVideo.getUserId(), LocalDateTime.now());
				} else {
					model.put("rescode", 208);
					model.put("susmsg", "Epesode not added yet");
					return ok(model);
				}
			} else {
				ct = new ContVideo(contVideo.getId(), contVideo.getVodId(), contVideo.getLastTime(), s.getThumbs(),
						String.valueOf(s.getAgeGroup()), s.getVdoUrl(), s.getTitle(), s.getmThumbs(),
						contVideo.getDuration(), contVideo.getUserId(), LocalDateTime.now());
			}
		} else {
			model.put("rescode", 208);
			model.put("susmsg", "We are Unable to The Video Aassociate With This Id:" + contVideo.getId());
			return ok(model);
		}
		return new ResponseEntity(contVideoServices.updateVideoByUser(ct), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/SyncAllVideo", method = RequestMethod.PATCH)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public boolean SyncAllUser(@RequestBody Genre url) {
		// LOG.info( "user."+transcation.getId());
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < 10000; i++) {
			SingleVideo pojoObjList = restTemplate
					.exchange(url.getGenreName(), HttpMethod.GET, null, new ParameterizedTypeReference<SingleVideo>() {
					}).getBody();
			System.out.println("OK" + pojoObjList);
		}
//    	 ResponseEntity<SingleVideo> responseEntity = restTemplate.exchange(
//    			    url.getGenreName(), 
//    			    HttpMethod.GET, 
//    			    null, 
//    			    new ParameterizedTypeReference<SingleVideo>() {
//    			    });
//    			SingleVideo pojoObjList = responseEntity.getBody();
//       System.out.println(pojoObjList);
		// salesRepository.syncAll(pojoObjList);
		return true;
		// return salesRepository.deleteAllUser();
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

	@RequestMapping(value = "/videoByGeners/{generName}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> videoByGeners(@PathVariable String generName, UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		List<SingleVideo> list = salesRepository.getVideoByGeners(generName);

		if (list != null) {
			// logger.error("Unable to create. A User with name {} already exist",
			// user.getName());
			return new ResponseEntity(new VideoResponse("OK", "200", list), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity(new VideoResponse(
					"Sorry Not Found any List on Database that Associated With " + generName + "Geners", "409", list),
					HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "/videoByCatogary/{catogary}/", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> videoByCatogary(@PathVariable String catogary, UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		List<SingleVideo> list = salesRepository.getVideoByCatogary(catogary);

		if (list != null) {
			// logger.error("Unable to create. A User with name {} already exist",
			// user.getName());
			return new ResponseEntity(new VideoResponse("OK", "200", list), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity(new VideoResponse(
					"Sorry Not Found any List on Database that Associated With " + catogary + "Catogary", "409", list),
					HttpStatus.CONFLICT);
		}

	}

//@Cacheable(value = "allVideoCatogary")
	@RequestMapping(value = "/allVideoByCatogary", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> allVideoByCatogary(UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", user);

		if (videoCatListServices.getVideoCatListByIdExist(0)) {
			return new ResponseEntity(videoCatListServices.getVideoCatListById(0), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity(
					new VideoCatList(null, "Sorry Not Found any List on Database that Associated With Catogary", "409"),
					HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "/addWatchList", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addWatchList(@RequestBody WatchList watchList, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(watchListServices.addContVideo(watchList), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getWatchList/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getWatchList(@PathVariable int id, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		List<SingleVideo> sv = new ArrayList<>();
		List<DashResponse> dlist = new ArrayList<>();
		List<WatchList> list = watchListServices.getWatchList(id);
		for (WatchList wl : list) {
			SingleVideo s = salesRepository.getVideoById(wl.getVodId());
			dlist.add(new DashResponse(s.getId(), s.getThumbs(), s.getTitle(), s.getVideoType(), s.getmThumbs()));
		}

		return new ResponseEntity(dlist, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/removeWatchList", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> removeWatchList(@RequestBody WatchList watchList, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(watchListServices.deleteContVideo(watchList), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/addFavourite", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> addFavouriteList(@RequestBody Favourite watchList, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(favouriteServices.addFavourite(watchList), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getFavouriteList/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> getFavouriteList(@PathVariable int id, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		List<SingleVideo> sv = new ArrayList<>();
		List<DashResponse> dlist = new ArrayList<>();

		List<Favourite> list = favouriteServices.getFavouriteList(id);
		if (list != null) {
			for (Favourite wl : list) {
				SingleVideo s = salesRepository.getVideoById(wl.getVodId());
				dlist.add(new DashResponse(s.getId(), s.getThumbs(), s.getTitle(), s.getVideoType(), s.getmThumbs()));
			}
		} else {
			return new ResponseEntity(new CustomErrorType("We Don't Found List With this UserId " + id),
					HttpStatus.CONFLICT);
		}

		return new ResponseEntity(dlist, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/removeFavouriteList", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://65.2.89.128:3000")
	public ResponseEntity<?> removeFavouriteList(@RequestBody Favourite watchList, UriComponentsBuilder ucBuilder) {
		// logger.error("Unable to create. A User with name {} already exist",
		// user.getName());
		return new ResponseEntity(favouriteServices.deleteFavourite(watchList), HttpStatus.ACCEPTED);
	}

}
