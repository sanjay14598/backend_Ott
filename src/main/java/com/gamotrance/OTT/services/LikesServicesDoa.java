package com.gamotrance.OTT.services;

import java.util.List;

import com.gamotrance.OTT.Model.Likes;

public interface LikesServicesDoa {
	
	Likes addLike(Likes likes);
	Likes updateLike(Likes likes);
	void deleteLike(Likes likes);
	
	Likes getbyIds(String userId,String vodId);
	
	List<Likes> getLikesByUser(String userId);
	List<Likes> getLikesByVideo(String videoId); 
	
	Likes getLikesExistance(String userId,String videoId);

}
