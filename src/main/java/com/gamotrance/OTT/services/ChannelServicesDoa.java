package com.gamotrance.OTT.services;

import java.util.List;

import com.gamotrance.OTT.Model.Channel;


public interface ChannelServicesDoa {
	 List<Channel> getAllChannel();
	 Channel getChannelById(int id);
	 Channel getChannelByName(String name);
	 List<Channel> getChannelByGenre(String genreName);
	 Channel addChannel(Channel channel);
	 Channel updateChannel(Channel channel);
	 boolean deleteChannel(Channel channel);


}
