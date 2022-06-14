package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gamotrance.OTT.Model.TransctiionField;

@Repository
public interface TransctionFieldRepo extends MongoRepository<TransctiionField, String>{

}
