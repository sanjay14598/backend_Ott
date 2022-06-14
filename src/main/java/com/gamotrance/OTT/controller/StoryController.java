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

import com.gamotrance.OTT.Model.Channel;
import com.gamotrance.OTT.Model.Story;
import com.gamotrance.OTT.services.StoryServices;

@RestController
@RequestMapping(value = "/Story")
@CrossOrigin(origins = "http://localhost:3000")
public class StoryController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final StoryServices channelServicesImplimentation;

	public StoryController(StoryServices channelServicesImplimentation) {
		this.channelServicesImplimentation = channelServicesImplimentation;
	}

	@RequestMapping(value = "/getAllStory", method = RequestMethod.GET)
	public List<Story> getAllStory() {
		LOG.info("Getting all List<Channel> getAllChannel.");
		return channelServicesImplimentation.getAllStory();
	}

	@RequestMapping(value = "/getStoryById/{id}", method = RequestMethod.GET)
	public Story getStoryById(@PathVariable int id) {
		LOG.info("Getting all users.");
		return channelServicesImplimentation.getStoryById(id);
	}
//	    @RequestMapping(value = "/getChannelByGenre/{genreName}", method = RequestMethod.GET)
//	    public List<Story> getChannelByGenre(@PathVariable String genreName) {
//	        LOG.info("Saving user."+genreName);
//	        return channelServicesImplimentation.getChannelByGenre(genreName);
//	    }
//	    

	@RequestMapping(value = "/addStory", method = RequestMethod.POST)
	public Story addStory(@RequestBody Story story) {
		LOG.info("Saving user." + story.getId());
		return channelServicesImplimentation.addStory(story);
	}

	@RequestMapping(value = "/deleteStory", method = RequestMethod.DELETE)
	public boolean deleteStory(@RequestBody Story channel) {
		LOG.info("user." + channel);
		channelServicesImplimentation.deleteStory(channel);
		return true;
	}

	@RequestMapping(value = "/getStoryByName/{name}/", method = RequestMethod.GET)
	public Story getStoryByName(@PathVariable String name) {
		LOG.info("user." + name);
		return channelServicesImplimentation.getStoryByName(name);
	}

	@RequestMapping(value = "/updateStory", method = RequestMethod.PATCH)
	public Story updateStory(@RequestBody Story channel) {
		LOG.info("user." + channel);
		return channelServicesImplimentation.updateStory(channel);
	}
}
