package com.gamotrance.OTT.services;

import java.util.List;

import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.UserView;



public interface SingleVideoDoa {
	List<SingleVideo> getAllSingleVideo();
	SingleVideo getVideoById(int id);
	SingleVideo getSingleVideoByName(String name);
	 List<SingleVideo> getSingleVideoByGenre(String genreName);
	 SingleVideo addSingleVideo(SingleVideo singleVideo);
	 SingleVideo updateSingleVideo(SingleVideo singleVideo);
	 boolean deleteSingleVideo(SingleVideo singleVideo);
	 List<SingleVideo> getVideoByChannel(int id);
	// boolean RemindMeVideo(String userId, String vodId);
	
}
