package com.gamotrance.OTT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.NotNull;

import com.gamotrance.OTT.Model.*;

public class OttUtil {

	public int getValue(UserType userType) {
		int value = 0;
		switch (userType) {
		case BASIC:
			value = 0;
			break;
		case ONEMONTH:
			value = 30;
			break;
		case ONEYEAR:
			value = 360;
			break;
		case THREEMONTHS:
			value = 90;
			break;
		case SIXMONTHS:
			value = 180;
			break;
		case DIAMOND:
			value = 360;
			break;
		case PRIMIUM:
			value = 180;
			break;
		case STANDARD:
			value = 60;
			break;

		default:
			break;
		}
		return value;
	}

	public static String OTP(int len) {

		// Using numeric values
		String numbers = "0123456789";

		// Using random method
		Random rndm_method = new Random();

		char[] otp = new char[len];

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		System.out.println("Generating OTP using random() : " + String.valueOf(otp));
		System.out.print("You OTP is : " + String.valueOf(otp));
		return String.valueOf(otp);
	}

	public SingleVideo getConvert(RowVideo rowVideo) {
		/*
		 * public SingleVideo(int id, @NotNull String title, @NotNull String
		 * description, Long likes, int channelId, List<Cast> casts, List<Genre> genres,
		 * Tralier tralier, List<Director> directors, List<Video> eps, List<Writer>
		 * writer, Long views, VideoType videoType, String thumbs, PeerType peerType,
		 * int ageGroup, String partNumber, String vdoUrl, List<Thumb> mThumbs, Double
		 * duration, String upcommingDate, List<String> countryList)
		 */

		List<Cast> casts = new ArrayList<>();
		casts.add(new Cast(0, rowVideo.getCasts(), null));
		List<Genre> genres = new ArrayList<>();
		genres.add(new Genre(rowVideo.getGenres()));
		List<Director> directors = new ArrayList<>();
		directors.add(new Director(0, rowVideo.getDirectors()));
		List<Writer> writer = new ArrayList<>();
		writer.add(new Writer(0, rowVideo.getWriter()));
		List<Thumb> mThumbs = new ArrayList<>();
		mThumbs.add(new Thumb(0, null, rowVideo.getThumbs()));
		Tralier tralier = new Tralier(0, rowVideo.getThumbs(), rowVideo.getTralierUrl(), null, null);
		List<Tralier> trl = new ArrayList<Tralier>();
		trl.add(tralier);
		List<String> langs = new ArrayList<>();
		langs.add(rowVideo.getLang());
		/*
		 * Tralier tralier=new Tralier(0, rowVideo.getThumbs(),
		 * rowVideo.getTralierUrl(), null, null); SingleVideo s=new
		 * SingleVideo(LocalDateTime.now().hashCode(), rowVideo.getTitle(),
		 * rowVideo.getDescription(), (long)0, 0, casts, genres, tralier, directors,
		 * null, writer, (long)0, rowVideo.getVideoType(),rowVideo.getLang(),
		 * rowVideo.getThumbs(), null, rowVideo.getAgeGroup(), null,
		 * rowVideo.getVdoUrl(), mThumbs, null, null, null);
		 */

		SingleVideo s = new SingleVideo(LocalDateTime.now().hashCode(), rowVideo.getTitle(), rowVideo.getDescription(),
				casts, genres, trl, directors, null, writer, rowVideo.getVideoType(), rowVideo.getThumbs(), null, 2,
				rowVideo.getVdoUrl(), null);
		/*
		 * (int id, @NotNull String title, @NotNull String description, List<Genre>
		 * genres, List<String> lang, String thumbs, String partNumber, String vdoUrl)
		 */
		/*
		 * (int id, @NotNull String title, @NotNull String description, List<Cast>
		 * casts, List<Genre> genres, List<Tralier> tralier, List<Director> directors,
		 * List<Video> eps, List<Writer> writer, VideoType videoType, String thumbs,
		 * List<Cataogry> peerType, int ageGroup, String vdoUrl, List<Thumb> mThumbs)
		 */

		return s;

	}

}
