package com.gamotrance.OTT.Model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class RowVideo{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
  
    private String title;
  
    private String description;
  
private String lang;
	public RowVideo(int id, String title, String description, String lang, Long likes, int channelId, String casts,
		String genres, String tralierUrl, String directors, String writer, Long views, VideoType videoType,
		String thumbs, int ageGroup, String vdoUrl, Double duration, String upcommingDate) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.lang = lang;
	this.likes = likes;
	this.channelId = channelId;
	this.casts = casts;
	this.genres = genres;
	this.tralierUrl = tralierUrl;
	this.directors = directors;
	this.writer = writer;
	this.views = views;
	this.videoType = videoType;
	this.thumbs = thumbs;
	this.ageGroup = ageGroup;
	this.vdoUrl = vdoUrl;
	this.duration = duration;
	this.upcommingDate = upcommingDate;
}
	public String getLang() {
	return lang;
}
public void setLang(String lang) {
	this.lang = lang;
}
	private Long likes;
    private int channelId;
    private String casts;
    private String genres;
    private String tralierUrl;

	private String directors;
   // private List<Video> eps;
	private String writer;
    private Long views;
    private VideoType videoType;
   	private String thumbs;
   // private PeerType peerType;
    
	private int ageGroup;


	public RowVideo(int id, String title, String description, Long likes, int channelId, String casts, String genres,
		String tralierUrl, String directors, String writer, Long views, VideoType videoType, String thumbs,
		int ageGroup, String vdoUrl, Double duration, String upcommingDate) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.likes = likes;
	this.channelId = channelId;
	this.casts = casts;
	this.genres = genres;
	this.tralierUrl = tralierUrl;
	this.directors = directors;
	this.writer = writer;
	this.views = views;
	this.videoType = videoType;
	this.thumbs = thumbs;
	this.ageGroup = ageGroup;
	this.vdoUrl = vdoUrl;
	this.duration = duration;
	this.upcommingDate = upcommingDate;
}
	public RowVideo() {
	super();
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getCasts() {
		return casts;
	}
	public void setCasts(String casts) {
		this.casts = casts;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getTralierUrl() {
		return tralierUrl;
	}
	public void setTralierUrl(String tralierUrl) {
		this.tralierUrl = tralierUrl;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public VideoType getVideoType() {
		return videoType;
	}
	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}
	public String getThumbs() {
		return thumbs;
	}
	public void setThumbs(String thumbs) {
		this.thumbs = thumbs;
	}
	public int getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getVdoUrl() {
		return vdoUrl;
	}
	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getUpcommingDate() {
		return upcommingDate;
	}
	public void setUpcommingDate(String upcommingDate) {
		this.upcommingDate = upcommingDate;
	}
	private String vdoUrl;
	
	private Double duration;
	private String upcommingDate;

}
