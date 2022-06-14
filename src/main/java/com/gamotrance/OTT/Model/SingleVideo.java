package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SingleVideo implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @Override
	public String toString() {
		return "SingleVideo [id=" + id + ", title=" + title + ", description=" + description + ", likes=" + likes
				+ ", channelId=" + channelId + ", casts=" + casts + ", genres=" + genres + ", tralier=" + tralier
				+ ", directors=" + directors + ", eps=" + eps + ", writer=" + writer + ", views=" + views
				+ ", videoType=" + videoType + ", thumbs=" + thumbs + ", peerType=" + peerType + ", ageGroup="
				+ ageGroup + ", partNumber=" + partNumber + ", vdoUrl=" + vdoUrl + ", mThumbs=" + mThumbs
				+ ", duration=" + duration + ", upcommingDate=" + upcommingDate + ", countryList=" + countryList + "]";
	}
	private Long likes;
	public SingleVideo(int id, @NotNull String title, @NotNull String description, List<Cast> casts, List<Genre> genres,
			List<Tralier> tralier, List<Director> directors, List<Video> eps, List<Writer> writer, VideoType videoType,
			String thumbs, List<Cataogry> peerType, int ageGroup, String vdoUrl, List<Thumb> mThumbs) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.casts = casts;
		this.genres = genres;
		this.tralier = tralier;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
		this.videoType = videoType;
		this.thumbs = thumbs;
		this.peerType = peerType;
		this.ageGroup = ageGroup;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
	}
	private Long disLike;
	private LocalDateTime createdAt;
    public SingleVideo(int id, @NotNull String title, @NotNull String description, Long likes, Long disLike,
			LocalDateTime createdAt, int channelId, List<Cast> casts, List<Genre> genres, List<Tralier> tralier,
			List<Director> directors, List<Video> eps, List<Writer> writer, Long views, VideoType videoType,
			String thumbs, List<Cataogry> peerType, int ageGroup, String partNumber, String vdoUrl, List<Thumb> mThumbs,
			Double duration, String upcommingDate, List<String> countryList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.disLike = disLike;
		this.createdAt = createdAt;
		this.channelId = channelId;
		this.casts = casts;
		this.genres = genres;
		this.tralier = tralier;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
		this.views = views;
		this.videoType = videoType;
		this.thumbs = thumbs;
		this.peerType = peerType;
		this.ageGroup = ageGroup;
		this.partNumber = partNumber;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.upcommingDate = upcommingDate;
		this.countryList = countryList;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Long getDisLike() {
		return disLike;
	}


	public SingleVideo(int id, @NotNull String title, @NotNull String description, Long likes, Long disLike,
			int channelId, List<Cast> casts, List<Genre> genres, List<Tralier> tralier, List<Director> directors,
			List<Video> eps, List<Writer> writer, Long views, VideoType videoType, String thumbs,
			List<Cataogry> peerType, int ageGroup, String partNumber, String vdoUrl, List<Thumb> mThumbs,
			Double duration, String upcommingDate, List<String> countryList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.disLike = disLike;
		this.channelId = channelId;
		this.casts = casts;
		this.genres = genres;
		this.tralier = tralier;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
		this.views = views;
		this.videoType = videoType;
		this.thumbs = thumbs;
		this.peerType = peerType;
		this.ageGroup = ageGroup;
		this.partNumber = partNumber;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.upcommingDate = upcommingDate;
		this.countryList = countryList;
	}


	public void setDisLike(Long disLike) {
		this.disLike = disLike;
	}
	private int channelId;
    private List<Cast> casts;
    private List<Genre> genres;
    private List<Tralier> tralier;
   
	private List<Director> directors;
   
	private List<Video> eps;
	private List<Writer> writer;
    private Long views;
    private VideoType videoType;
	public List<Tralier> getTralier() {
		return tralier;
	}


	public void setTralier(List<Tralier> tralier) {
		this.tralier = tralier;
	}


	

	private String thumbs;
    private List<Cataogry> peerType;
   
	public SingleVideo(int id, @NotNull String title, @NotNull String description, Long likes, int channelId,
			List<Cast> casts, List<Genre> genres, List<Tralier> tralier, List<Director> directors, List<Video> eps,
			List<Writer> writer, Long views, VideoType videoType, String thumbs, List<Cataogry> peerType, int ageGroup,
			String partNumber, String vdoUrl, List<Thumb> mThumbs, Double duration, String upcommingDate,
			List<String> countryList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.channelId = channelId;
		this.casts = casts;
		this.genres = genres;
		this.tralier = tralier;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
		this.views = views;
		this.videoType = videoType;
		this.thumbs = thumbs;
		this.peerType = peerType;
		this.ageGroup = ageGroup;
		this.partNumber = partNumber;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.upcommingDate = upcommingDate;
		this.countryList = countryList;
	}


	public List<Cataogry> getPeerType() {
		return peerType;
	}


	public void setPeerType(List<Cataogry> peerType) {
		this.peerType = peerType;
	}
	private int ageGroup;
    public SingleVideo(int id, @NotNull String title, @NotNull String description, Long likes, int channelId,
			List<Cast> casts, List<Genre> genres, List<Director> directors, List<Video> eps, List<Writer> writer,
			Long views, VideoType videoType, String thumbs, int ageGroup, String partNumber, String vdoUrl,
			List<Thumb> mThumbs, Double duration, String upcommingDate, List<String> countryList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.channelId = channelId;
		this.casts = casts;
		this.genres = genres;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
		this.views = views;
		this.videoType = videoType;
		this.thumbs = thumbs;
		this.ageGroup = ageGroup;
		this.partNumber = partNumber;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.upcommingDate = upcommingDate;
		this.countryList = countryList;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

	public SingleVideo(int id, @NotNull String title, @NotNull String description, String thumbs, String vdoUrl) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.thumbs = thumbs;
		this.vdoUrl = vdoUrl;
	}


	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}
	private String partNumber;
	private String vdoUrl;
	private List<Thumb> mThumbs;
	private Double duration;
	private String upcommingDate;
	private List<String> countryList;


	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	

	public String getUpcommingDate() {
		return upcommingDate;
	}

	public void setUpcommingDate(String upcommingDate) {
		this.upcommingDate = upcommingDate;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public List<Writer> getWriter() {
		return writer;
	}


	public void setWriter(List<Writer> writer) {
		this.writer = writer;
	}


	public List<Thumb> getmThumbs() {
		return mThumbs;
	}


	public void setmThumbs(List<Thumb> mThumbs) {
		this.mThumbs = mThumbs;
	}


	public List<Video> getEps() {
		return eps;
	}


	public void setEps(List<Video> eps) {
		this.eps = eps;
	}


	public String getPartNumber() {
		return partNumber;
	}


	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}


	public String getThumbs() {
		return thumbs;
	}


	public void setThumbs(String thumbs) {
		this.thumbs = thumbs;
	}


	public SingleVideo() {
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
	public List<Cast> getCasts() {
		return casts;
	}
	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public List<Director> getDirectors() {
		return directors;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
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
	public String getVdoUrl() {
		return vdoUrl;
	}
	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
}
