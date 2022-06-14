package com.gamotrance.OTT.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gamotrance.OTT.Model.Cataogry;
import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.DashResponse;
import com.gamotrance.OTT.Model.Favourite;
import com.gamotrance.OTT.Model.PeerType;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.WatchList;
import com.gamotrance.OTT.services.CatogarryServices;
import com.gamotrance.OTT.services.ChannelServicesImplimentation;
import com.gamotrance.OTT.services.FavouriteServices;

@RestController
@RequestMapping(value = "/Channel")
@CrossOrigin(origins = "http://localhost:3000")
public class ChannelController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final ChannelServicesImplimentation channelServicesImplimentation;
	private final CatogarryServices catogarryServices;

	@Value("${file.upload-dir}")
	private String filRootDir;

	public ChannelController(ChannelServicesImplimentation channelServicesImplimentation,
			CatogarryServices catogarryServices) {
		this.channelServicesImplimentation = channelServicesImplimentation;

		this.catogarryServices = catogarryServices;
	}

	@RequestMapping(value = "/getAllChannel", method = RequestMethod.GET)
	public List<Channel> getAllChannel() {
		LOG.info("Getting all List<Channel> getAllChannel.");
		return channelServicesImplimentation.getAllChannel();
	}

	@RequestMapping(value = "/getChannelById/{id}", method = RequestMethod.GET)
	public Channel getChannelById(@PathVariable int id) {
		LOG.info("Getting all users.");
		return channelServicesImplimentation.getChannelById(id);
	}

	@RequestMapping(value = "/getChannelByGenre/{genreName}", method = RequestMethod.GET)
	public List<Channel> getChannelByGenre(@PathVariable String genreName) {
		LOG.info("Saving user." + genreName);
		return channelServicesImplimentation.getChannelByGenre(genreName);
	}

	@RequestMapping(value = "/addChannel", method = RequestMethod.POST)
	public Channel addChannel(@RequestBody Channel channel) {
		LOG.info("Saving user." + channel.getId());
		return channelServicesImplimentation.addChannel(channel);
	}

	@RequestMapping(value = "/deleteChannel", method = RequestMethod.DELETE)
	public boolean deleteChannel(@RequestBody Channel channel) {
		LOG.info("user." + channel);
		channelServicesImplimentation.deleteChannel(channel);
		return true;
	}

	@RequestMapping(value = "/getChannelByName/{name}/", method = RequestMethod.GET)
	public Channel getChannelByName(@PathVariable String name) {
		LOG.info("user." + name);
		return channelServicesImplimentation.getChannelByName(name);
	}

	@RequestMapping(value = "/updateChannel", method = RequestMethod.PATCH)
	public Channel updateChannel(@RequestBody Channel channel) {
		LOG.info("user." + channel);
		return channelServicesImplimentation.updateChannel(channel);
	}

	@RequestMapping(value = "/addFollow/{id}", method = RequestMethod.POST)
	public String addFollow(@PathVariable Integer id, String userId) {
//	        LOG.info( "user."+channel);
//	        return channelServicesImplimentation.updateChannel(channel);
		Channel channel = channelServicesImplimentation.getChannelById(id);
		List<String> list = new ArrayList<String>(channel.getSubscribers());
		if (!list.contains(userId)) {
			list.add(userId);
			channelServicesImplimentation.updateChannel(new Channel(channel.getId(), channel.getTitle(),
					channel.getDescription(), channel.getProfileUrl(), list, channel.getGenres()));
			return String.valueOf(list.size());
		} else {
			return "user already subscribe to this channel";
		}

	}

	@RequestMapping(value = "/updateFollow/{id}", method = RequestMethod.PATCH)
	public String updateFollow(@PathVariable Integer id, String userId) {
//	        LOG.info( "user."+channel);
//	        return channelServicesImplimentation.updateChannel(channel);
		Channel channel = channelServicesImplimentation.getChannelById(id);
		List<String> list = new ArrayList<String>(channel.getSubscribers());
		list.remove(userId);
		channelServicesImplimentation.updateChannel(new Channel(channel.getId(), channel.getTitle(),
				channel.getDescription(), channel.getProfileUrl(), list, channel.getGenres()));
		return String.valueOf(list.size());

	}

	// List<Channel> getAllChannel();
	// Channel getChannelById(int id);
	// Channel getChannelByName(String name);
	// List<Channel> getChannelByGenre(String genreName);
	// Channel addChannel(Channel channel);
	// Channel updateChannel(Channel channel);
	// boolean deleteChannel(Channel channel);

	String[] ar = { "REGULAR", "RECOMENDED", "ORIGINALS", "PRIMIUM", "SHOWINDEMAND", "HINDIMOVIE", "MOVIE", "SORTMOVIE",
			"HINDISORTMOVIE", "TRAILER", "SOUTH", "BESTINBHOJPURI", "TRENDING", "ROMANTIC", "YOUTH", "COMEDIES",
			"MUSICVIDEO", "SUPERNATURALS", "ENGLISHHORROR", "ENGLISHEROTIC", "SCIFI", "ADVANTURE", "ACTION",
			"HOLLYWOODMOVIE", "WEBSERIES", "ENGLISHWEBSERIES", "ENGLISHSORTMOVIE", "TOP10", "HIINDIWEBSERIES",
			"ENGLISHMUSIC", "COMMINGSOON", "DEFAULT" };

	@RequestMapping(value = "/addCatoray", method = RequestMethod.POST)
	public Boolean addCatoray(@RequestBody Cataogry channel) {
		// LOG.info("Saving user."+channel.getId());
//	        for(String s:ar)
//	        {
		catogarryServices.addCataogry(channel);
		// }
		return true;
	}

	@RequestMapping(value = "/addListCatoray", method = RequestMethod.POST)
	public Boolean addListCatoray(@RequestBody List<Cataogry> channel) {
		// LOG.info("Saving user."+channel.getId());
		for (Cataogry s : channel) {
			catogarryServices.addCataogry(s);
		}
		return true;
	}

	@RequestMapping(value = "/deleteCatoray", method = RequestMethod.DELETE)
	public boolean deleteCatoray(@RequestBody Cataogry channel) {
		LOG.info("user." + channel);
		catogarryServices.deleteCataogry(channel);
		return true;
	}

	@RequestMapping(value = "/deleteAllCatoray", method = RequestMethod.DELETE)
	public boolean deleteCatoray() {
		// LOG.info( "user."+channel);
		catogarryServices.deleteAllCataogry();
		return true;
	}

	@RequestMapping(value = "/getCatorayList", method = RequestMethod.GET)
	public List<Cataogry> getCatorayList() {
		LOG.info("user.");
		return catogarryServices.getAllCataogry();
	}
//	    @RequestMapping(value = "/updateChannel", method = RequestMethod.PATCH)
//	    public Channel updateChannel(@RequestBody Channel channel) {
//	        LOG.info( "user."+channel);
//	        return channelServicesImplimentation.updateChannel(channel);
//	    }

}
