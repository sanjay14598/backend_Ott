package com.gamotrance.OTT.Model;

import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.util.Date;

@Document
public class UserDocument {
    @Id
    int id;

    public UserDocument() {
    }

    private Long userId;
    private String documnetName;
    private String uri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDocumnetName() {
        return documnetName;
    }

    public void setDocumnetName(String documnetName) {
        this.documnetName = documnetName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getSubbimtionDate() {
        return subbimtionDate;
    }

    public void setSubbimtionDate(Date subbimtionDate) {
        this.subbimtionDate = subbimtionDate;
    }

    public UserDocument(int id, Long userId, String documnetName, String uri, Date subbimtionDate) {
        this.id = id;
        this.userId = userId;
        this.documnetName = documnetName;
        this.uri = uri;
        this.subbimtionDate = subbimtionDate;
    }

    private Date subbimtionDate;
}
