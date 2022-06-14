package com.gamotrance.OTT.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.gamotrance.OTT.Doa.SingleVideoRepo;
import com.gamotrance.OTT.Model.SingleVideo;
import com.gamotrance.OTT.Model.User;
import com.gamotrance.OTT.Model.UserView;


@Repository
public class SingleVideoServicesImplimentation implements SingleVideoDoa{

	@Autowired
	SingleVideoRepo singleVideoRepo;
	
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	 
	 
	@Override
	public List<SingleVideo> getAllSingleVideo() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(SingleVideo.class);
	}
	
	public boolean syncAll(List<SingleVideo> user) {
		// TODO Auto-generated method stub
		singleVideoRepo.saveAll(user);
		return true;
	}

	@Override
	public SingleVideo getVideoById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        SingleVideo singleVideo = mongoTemplate.findById(id, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}

	@Override
	public SingleVideo getSingleVideoByName(String name) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        SingleVideo singleVideo = mongoTemplate.findOne(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}

	@Override
	public List<SingleVideo> getSingleVideoByGenre(String genreName) {
		Query query = new Query();
        query.addCriteria(Criteria.where("genres.genreName").is(genreName));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}

	
	public List<SingleVideo> getVideoByVodType(String vodtype) {
		Query query = new Query();
        query.addCriteria(Criteria.where("videoType").is(vodtype));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}
	@Override
	public SingleVideo addSingleVideo(SingleVideo singleVideo) {
		singleVideo.setId(LocalDateTime.now().hashCode());
		
//		MediaMetadataRetriever media = new MediaMetadataRetriever();
//		media.setDataSource(result);
//		String duration = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//		int time = Integer.parseInt(duration);
		// TODO Auto-generated method stub
		return mongoTemplate.save(singleVideo);
	}

	@Override
	public SingleVideo updateSingleVideo(SingleVideo singleVideo) {
		// TODO Auto-generated method stub
		 	Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(singleVideo.getId()));
	        SingleVideo user1 = mongoTemplate.findOne(query, SingleVideo.class);
	     
	        if(user1!=null)
	            {
	        	return mongoTemplate.save(singleVideo);
	        }
	        return null;	
	}
	

	@Override
	public boolean deleteSingleVideo(SingleVideo singleVideo) {
		// TODO Auto-generated method stub
		try {
		singleVideoRepo.delete(singleVideo);
		return true;
		}catch(Exception e)
		{
			return false;
		}	
	}
	public boolean deleteSingleVideoById(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        SingleVideo user1 = mongoTemplate.findOne(query, SingleVideo.class);
     
        if(user1!=null)
            {
        	 singleVideoRepo.delete(user1);
        	return true;
        }else
        {
        	return false;
        }

	}
	public boolean deleteAllVideo() {
		// TODO Auto-generated method stub
		try {
		singleVideoRepo.deleteAll();
		return true;
		}catch(Exception e)
		{
			return false;
		}	
	}

	@Override
	public List<SingleVideo> getVideoByChannel(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("channelId").is(id));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}
	public List<SingleVideo> getVideoByGeners(String gener) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("videoType").is(gener));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}
	public List<SingleVideo> getVideoByCatogary(String peerType) {
		// TODO Auto-generated method stub
		Query query = new Query();
        query.addCriteria(Criteria.where("peerType.peer").in(peerType));
        List<SingleVideo> singleVideo = mongoTemplate.find(query, SingleVideo.class);
        System.out.println("userTest2 - " + singleVideo);
		return singleVideo;
	}
	public List<SingleVideo> getVideoByCountry(String countryName) {
		// TODO Auto-generated method stub
		List<SingleVideo> lst=mongoTemplate.findAll(SingleVideo.class);
		List<SingleVideo> finalList=new ArrayList<SingleVideo>();
		if(lst!=null)
		for(SingleVideo sg:lst)
		{
			if(sg.getCountryList()!=null)
			{
				for(String ct:sg.getCountryList())
					if(countryName.equalsIgnoreCase(ct))
					finalList.add(sg);		
			}
		
		}
		return finalList;
	}
}
