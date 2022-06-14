package com.gamotrance.OTT.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class UserFcmToken {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserFcmToken(Long id, Long userId, String token) {
        this.id = id;
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Id
    private Long id;




    private Long userId;
    private String token;

    public UserFcmToken() {
    }
}
