package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Device {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    public String deviceName;
    public String deviceType;
    public String appVersion;
    public String deviceToken;
   

}
