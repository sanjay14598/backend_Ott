package com.gamotrance.OTT.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;

import com.gamotrance.OTT.OttUtil;
import com.gamotrance.OTT.Model.CanvasjsChartData;
import com.gamotrance.OTT.Model.Cast;
import com.gamotrance.OTT.Model.Plan;
import com.gamotrance.OTT.Model.RowVideo;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.Subdata;
import com.gamotrance.OTT.Model.Tralier;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserType;
import com.gamotrance.OTT.services.CatogarryServices;
import com.gamotrance.OTT.services.DashboardServices;
import com.gamotrance.OTT.services.PlanServices;
import com.gamotrance.OTT.services.RowVideoServices;
import com.gamotrance.OTT.services.SingleVideoServicesImplimentation;
import com.gamotrance.OTT.services.UserServicesImplimentation;

@Controller
public class DashboardController {

	DashboardServices dashboardServices;
	PlanServices planServices;
	RowVideoServices rowVideoServices;
	UserServicesImplimentation userServicesImplimentation;
	CatogarryServices catogarryServices;
	SingleVideoServicesImplimentation singleVideoServicesImplimentation;

	public DashboardController(CatogarryServices catogarryServices,
			UserServicesImplimentation userServicesImplimentation, RowVideoServices rowVideoServices,
			PlanServices planServices, SingleVideoServicesImplimentation singleVideoServicesImplimentation,
			DashboardServices dashboardServices) {
		super();
		this.dashboardServices = dashboardServices;
		// this.canvasjsChartService=canvasjsChartService;
		this.userServicesImplimentation = userServicesImplimentation;
		this.rowVideoServices = rowVideoServices;
		this.planServices = planServices;
		this.catogarryServices = catogarryServices;
		this.singleVideoServicesImplimentation = singleVideoServicesImplimentation;
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("dash", dashboardServices.getValue());
		List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
		/*
		 * System.out.println("dash" + dashboardServices.getValue());
		 * System.out.println("dataPointsList" + canvasjsDataList.toString());
		 */
		model.addAttribute("dataPointsList", canvasjsDataList);
		model.addAttribute("dashv", rowVideoServices.getAllRowVideo());
		return "index.html";
	}

	@RequestMapping("/addcontent")
	public String addcontent(Model model) {
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());

		SingleVideo sv = new SingleVideo();
		List<Cast> clist = new ArrayList<>();
		clist.add(new Cast());
		clist.add(new Cast());
		sv.setCasts(clist);
		/*
		 * List<Tralier> tl=new ArrayList<>(); tl.add(new Tralier());
		 */
		// sv.setTralier(tl);
		model.addAttribute("rowVideo", sv);
		model.addAttribute("dash", catogarryServices.getAllCataogry());
		return "addcontent";
	}

	// addmoreCast
	@RequestMapping("/addmoreCast")
	public String addmoreCast(@ModelAttribute("rowVideo") SingleVideo singleVideo,
			@RequestParam(value = "action", required = true) String action, Model model) {
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
		System.out.println("call ok");

		// SingleVideo sv = new SingleVideo();
		List<Cast> clist = singleVideo.getCasts();
		clist.add(new Cast());
		singleVideo.setCasts(clist);

		/*
		 * List<Tralier> tl=new ArrayList<>(); tl.add(new Tralier());
		 */
		// sv.setTralier(tl);
		model.addAttribute("rowVideo", singleVideo);
		model.addAttribute("dash", catogarryServices.getAllCataogry());
		return "redirect:/addcontent";
	}
	/*
	 * @RequestMapping("/referal") public String referal(Model model) {
	 * model.addAttribute("rowVideo", referalServices.getReferalById(0));
	 * 
	 * return "referal"; }
	 * 
	 * @RequestMapping("/updatereferal") public String
	 * referal(@ModelAttribute("rowVideo") Referal referal, Model model) {
	 * referalServices.updateReferal(referal); model.addAttribute("rowVideo",
	 * referalServices.getReferalById(0)); return "referal"; }
	 */

	/*
	 * @RequestMapping("/newarrival") public String newarrival(Model model) {
	 * model.addAttribute("rowVideo", new UpcomingVideos());
	 * model.addAttribute("dash",
	 * singleVideoServicesImplimentation.getAllSingleVideo()); return "newarriaval";
	 * }
	 * 
	 * @PostMapping("/savenewarrival") public String
	 * savenewarrival(@ModelAttribute("rowVideo") UpcomingVideos watchList, Model
	 * model) {
	 * 
	 * // dBannerServices.addDBanner(dBanner); SingleVideo s =
	 * singleVideoServicesImplimentation.getVideoById(Integer.valueOf(watchList.
	 * getVideoid())); watchList.setThumb(s.getThumbs());
	 * watchList.setTitle(s.getTitle());
	 * recentUpcomingVideoServices.addUpcomingVideos(watchList);
	 * model.addAttribute("dash", recentUpcomingVideoServices.getUpcomingVideo());
	 * return "listnewarrival"; }
	 * 
	 * @RequestMapping("/listnewarrival") public String listnewarrival(Model model)
	 * { model.addAttribute("dash", recentUpcomingVideoServices.getUpcomingVideo());
	 * // List<List<Map<Object, Object>>> canvasjsDataList =
	 * dashboardServices.getChartList(); //
	 * System.out.println("dataPointsList"+canvasjsDataList.toString()); //
	 * model.addAttribute("dataPointsList", canvasjsDataList); return
	 * "listnewarrival"; }
	 */

	@RequestMapping("/addsub")
	public String addsub(Model model) {
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());

		/*
		 * List<Tralier> tl=new ArrayList<>(); tl.add(new Tralier());
		 */
		// sv.setTralier(tl);
		model.addAttribute("rowVideo", new Subdata());
		return "subpages";
	}

	@RequestMapping("/addslider")
	public String addslider(Model model) {
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());

		/*
		 * List<Tralier> tl=new ArrayList<>(); tl.add(new Tralier());
		 */
		// sv.setTralier(tl);
		// model.addAttribute("rowVideo", new DBanner());
		return "addslider";
	}

	/*
	 * @PostMapping("/addReferal") public String
	 * addReferal(@ModelAttribute("rowVideo") Referal referal, Model model) {
	 * 
	 * referalServices.updateReferal(referal); model.addAttribute("dash",
	 * dBannerServices.getDBannerALL()); return "listslider"; }
	 */

	/*
	 * @PostMapping("/saveslider") public String
	 * saveslider(@ModelAttribute("rowVideo") DBanner dBanner, Model model) {
	 * 
	 * dBannerServices.addDBanner(dBanner); model.addAttribute("dash",
	 * dBannerServices.getDBannerALL()); return "listslider"; }
	 * 
	 * @RequestMapping("/listslider") public String saveslider(Model model) {
	 * 
	 * // dBannerServices.addDBanner(dBanner); model.addAttribute("dash",
	 * dBannerServices.getDBannerALL()); return "listslider"; }
	 * 
	 * @RequestMapping("/editslider/{id}") public String
	 * editslider(@PathVariable(value = "id") int id, Model model) { Model model=new
	 * Model(); // model.addAttribute("dash", dashboardServices.getValue()); //
	 * List<List<Map<Object, Object>>> canvasjsDataList =
	 * dashboardServices.getChartList(); //
	 * System.out.println("dataPointsList"+canvasjsDataList.toString());
	 * model.addAttribute("rowVideo", dBannerServices.getDBannerByIds(id)); return
	 * "editslider";
	 * 
	 * }
	 * 
	 * @PostMapping("/updateslider") public String
	 * updateslider(@ModelAttribute("rowVideo") DBanner dBanner, Model model) { //
	 * System.out.println("single video"+singleVideo.getDescription());
	 * 
	 * if (result.hasErrors()) { return "add-user"; }
	 * 
	 * dBannerServices.updateDBanner(dBanner); model.addAttribute("dash",
	 * dBannerServices.getDBannerALL());
	 * 
	 * return "listslider"; }
	 */

	@RequestMapping("/editContentPage/")
	public ModelAndView editContentPage(@RequestParam(value = "id") int id) {
		/* Model model=new Model(); */
		ModelAndView model = new ModelAndView("editcontent");
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
		model.addObject("rowVideo", rowVideoServices.getRowVideoById(id));
		return model;
		// return "redirect:/publishvideolist";

	}

	@RequestMapping("/editContent/{id}")
	public String editContent(@PathVariable(value = "id") int id, Model model) {
		/* Model model=new Model(); */
		/* ModelAndView model= new ModelAndView("editcontent"); */

//    	model.addAttribute("dash", dashboardServices.getValue());
//     	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
		/*
		 * model.addObject("rowVideo", rowVideoServices.getRowVideoById(id)); return
		 * model;
		 */
		model.addAttribute("rowVideo", rowVideoServices.getRowVideoById(id));
		return "editcontent";

	}
	/*
	 * @RequestMapping("/editPublish/{id}") public String
	 * editPublish(@PathVariable(value = "id") int id, Model model) {
	 * 
	 * model.addAttribute("rowVideo", rowVideoServices.getRowVideoById(id)); return
	 * "editcontent";
	 * 
	 * }
	 */

	@RequestMapping("/publish/{id}")
	public String publish(@PathVariable(value = "id") int id, Model model) {
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
		OttUtil ot = new OttUtil();
		SingleVideo rw = ot.getConvert(rowVideoServices.getRowVideoById(id));
		System.out.println(rw.toString());
		singleVideoServicesImplimentation.addSingleVideo(rw);
		try {
			flushall("redis-cli flushall");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("vdo", singleVideoServicesImplimentation.getAllSingleVideo());
		return "publishcontent";
	}

	@RequestMapping("/addvideo")
	public String addvideo(Model model) {
		model.addAttribute("dash", rowVideoServices.getAllRowVideo());
//    	model.addAttribute("dash", dashboardServices.getValue());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "listcontent";
	}

	@RequestMapping("/listcontent")
	public String listcontent(Model model) {
		model.addAttribute("dash", rowVideoServices.getAllRowVideo());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "listcontent";
	}

	@RequestMapping("/userlist")
	public String userlist(Model model) {

		List<User> list = userServicesImplimentation.getAllUser();
		Collections.reverse(list);

		model.addAttribute("dash", list);
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "userlist";
	}

	@RequestMapping("/publishvideolist")
	public String publishvideolist(Model model) {
		model.addAttribute("vdo", singleVideoServicesImplimentation.getAllSingleVideo());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "publishcontent";
	}

	@RequestMapping("/trending")
	public String trending(Model model) {
		model.addAttribute("vdo", singleVideoServicesImplimentation.getAllSingleVideo());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "trendinglist";
	}

	@RequestMapping("/publishvideoEdit/{id}")
	public String publishvideoEdit(Model model) {
		model.addAttribute("vdo", singleVideoServicesImplimentation.getAllSingleVideo());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "publishcontent";
	}

	@RequestMapping("/publishvideodelete/{id}")
	public String publishvideodelete(Model model) {
		model.addAttribute("vdo", singleVideoServicesImplimentation.getAllSingleVideo());
//    	List<List<Map<Object, Object>>> canvasjsDataList = dashboardServices.getChartList();
//    	System.out.println("dataPointsList"+canvasjsDataList.toString());
//    	model.addAttribute("dataPointsList", canvasjsDataList);
		return "publishcontent";
	}

	/*
	 * @PostMapping("/addCast") public String addCast(String person) {
	 * //personService.addContact(person); return "index :: contacts"; // returning
	 * the updated section }
	 */
	@PostMapping("/addSupcription")
	public String addSupcription(@ModelAttribute("rowVideo") Subdata subdata, Model model) {
		// personService.addContact(person);

		User user = userServicesImplimentation.getPhoneVerify(subdata.getMobile());

		if (subdata.getDuration() == 1) {
			LocalDate ld = LocalDate.now().plusDays(30);
			user.setExpdate(ld.toString());
			user.setUserType(UserType.ONEMONTH);
		} else if (subdata.getDuration() == 3) {
			LocalDate ld = LocalDate.now().plusDays(90);
			user.setExpdate(ld.toString());
			user.setUserType(UserType.THREEMONTHS);
		} else if (subdata.getDuration() == 6) {
			LocalDate ld = LocalDate.now().plusDays(180);
			user.setExpdate(ld.toString());
			user.setUserType(UserType.SIXMONTHS);
		}
		userServicesImplimentation.updateUser(user);
		List<User> list = userServicesImplimentation.getAllUser();
		Collections.reverse(list);

		model.addAttribute("dash", list);
//    
		return "userlist.html";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") int id) {

		// call delete employee method
		userServicesImplimentation.deleteUserById(id);
		// rowVideoServices.deleteRowVideo(id);
		return "redirect:/userlist";
	}

	@PostMapping("/addrowVideo")
	public String addSingleVideo(@ModelAttribute("rowVideo") SingleVideo singleVideo,
			@RequestParam(value = "action", required = true) String action, Model model) {
		System.out.println("single video" + singleVideo.toString());
		/*
		 * if (result.hasErrors()) { return "add-user"; }
		 */
		String ret = "";
		switch (action) {
		case "addmorecast":
			// do stuff

			model.addAttribute("rowVideo", singleVideo);
			model.addAttribute("dash", catogarryServices.getAllCataogry());
			ret = "redirect:/addcontent";

			break;
		case "cancel":
			// do stuff
			break;
		case "newthing":
			// do stuff
			break;
		default:
			singleVideoServicesImplimentation.addSingleVideo(singleVideo);
			// rowVideoServices.addRowVideo(singleVideo);
			model.addAttribute("dash", rowVideoServices.getAllRowVideo());
			ret = "redirect:/listcontent";
			break;

		}
		singleVideoServicesImplimentation.addSingleVideo(singleVideo);
		// rowVideoServices.addRowVideo(singleVideo);
		model.addAttribute("dash", rowVideoServices.getAllRowVideo());

		return ret;
	}

	@PostMapping("/updaterowVideo")
	public String updateSingleVideo(@ModelAttribute("rowVideo") RowVideo singleVideo, Model model) {
		System.out.println("single video" + singleVideo.getId());
		/*
		 * if (result.hasErrors()) { return "add-user"; }
		 */
		rowVideoServices.updateRowVideo(singleVideo);
		model.addAttribute("dash", rowVideoServices.getAllRowVideo());

		return "listcontent.html";
	}

	// Added to test 500 page
	@RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
	public void error500() throws Exception {
		throw new Exception();
	}

	@GetMapping("/deleteSingleVideo/{id}")
	public String deleteSingleVideo(@PathVariable(value = "id") int id) {

		// call delete employee method
		rowVideoServices.deleteRowVideo(id);
		return "redirect:/listcontent";
	}

	@GetMapping("/deleteSingleVideoR/{id}")
	public String deleteSingleVideoR(@PathVariable(value = "id") int id) {

		// call delete employee method
		singleVideoServicesImplimentation.deleteSingleVideoById(id);
		return "redirect:/publishvideolist";
	}

	/*
	 * @GetMapping("/deleteslider/{id}") public String
	 * deleteslider(@PathVariable(value = "id") int id) {
	 * 
	 * // call delete employee method dBannerServices.deleteDBanner(id); return
	 * "redirect:/listslider"; }
	 */

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

	/*
	 * @RequestMapping("/listcomplent") public String listcomplent(Model model) {
	 * model.addAttribute("dash", helpServices.getHelpALL()); return "listcomplent";
	 * }
	 * 
	 * @RequestMapping("/complentreplay/{id}") public String
	 * complentreplay(@PathVariable(value = "id") int id, Model model) {
	 * System.out.println("id"+id); model.addAttribute("dt", null);
	 * model.addAttribute("rowVideo", helpServices.getHelpByIds(id)); return
	 * "replaycomplent"; }
	 * 
	 * @RequestMapping("/savecomplentreplay") public String
	 * savecomplentreplay(@ModelAttribute("rowVideo") Help help) {
	 * helpServices.updateHelp(help); return "redirect:/listcomplent"; }
	 */
	/*
	 * @RequestMapping("/editContent/{id}") public String
	 * editContent(@PathVariable(value = "id") int id,Model model) { Model model=new
	 * Model(); // model.addAttribute("dash", dashboardServices.getValue()); //
	 * List<List<Map<Object, Object>>> canvasjsDataList =
	 * dashboardServices.getChartList(); //
	 * System.out.println("dataPointsList"+canvasjsDataList.toString());
	 * model.addAttribute("rowVideo", rowVideoServices.getRowVideoById(id)); return
	 * "editcontent";
	 * 
	 * }
	 */

}
