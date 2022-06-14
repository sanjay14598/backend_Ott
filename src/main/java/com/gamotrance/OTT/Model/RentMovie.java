package com.gamotrance.OTT.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RentMovie implements Serializable{
	public RentMovie() {
		super();
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private List<Cast> casts;
    private VideoType videoCat;
    private PeerType videoSubCat;

	public RentMovie(int id, @NotNull String title, @NotNull String description, List<Cast> casts, VideoType videoCat,
			PeerType videoSubCat, List<String> tags, List<Genre> genres, Tralier tralier, String thumbs, int ageGroup,
			String partNumber, String vdoUrl, List<Thumb> mThumbs, Double duration, String upcommingDate,
			List<String> countryList, MPrice price, List<Director> directors, List<Video> eps, List<Writer> writer) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.casts = casts;
		this.videoCat = videoCat;
		this.videoSubCat = videoSubCat;
		this.tags = tags;
		this.genres = genres;
		this.tralier = tralier;
		this.thumbs = thumbs;
		this.ageGroup = ageGroup;
		this.partNumber = partNumber;
		this.vdoUrl = vdoUrl;
		this.mThumbs = mThumbs;
		this.duration = duration;
		this.upcommingDate = upcommingDate;
		this.countryList = countryList;
		this.price = price;
		this.directors = directors;
		this.eps = eps;
		this.writer = writer;
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
	public List<Cast> getCasts() {
		return casts;
	}
	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}
	public VideoType getVideoCat() {
		return videoCat;
	}
	public void setVideoCat(VideoType videoCat) {
		this.videoCat = videoCat;
	}
	public PeerType getVideoSubCat() {
		return videoSubCat;
	}
	public void setVideoSubCat(PeerType videoSubCat) {
		this.videoSubCat = videoSubCat;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public Tralier getTralier() {
		return tralier;
	}
	public void setTralier(Tralier tralier) {
		this.tralier = tralier;
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
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getVdoUrl() {
		return vdoUrl;
	}
	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
	public List<Thumb> getmThumbs() {
		return mThumbs;
	}
	public void setmThumbs(List<Thumb> mThumbs) {
		this.mThumbs = mThumbs;
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
	public List<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}
	
	public MPrice getPrice() {
		return price;
	}
	public void setPrice(MPrice price) {
		this.price = price;
	}
	public List<Director> getDirectors() {
		return directors;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
	public List<Video> getEps() {
		return eps;
	}
	public void setEps(List<Video> eps) {
		this.eps = eps;
	}
	public List<Writer> getWriter() {
		return writer;
	}
	public void setWriter(List<Writer> writer) {
		this.writer = writer;
	}
	private List<String> tags;
	private List<Genre> genres;
    private Tralier tralier;
    private String thumbs;
    private int ageGroup;
    private String partNumber;
	private String vdoUrl;
	private List<Thumb> mThumbs;
	private Double duration;
	private String upcommingDate;
	private List<String> countryList;
	private MPrice price;
	private List<Director> directors;
    private List<Video> eps;
	private List<Writer> writer;
}
