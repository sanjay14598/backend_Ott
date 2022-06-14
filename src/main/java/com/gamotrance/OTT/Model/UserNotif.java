package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserNotif {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    int id;
	    @NotNull
	    private String token;
	    public UserNotif() {
			super();
		}
		public UserNotif(int id, @NotNull String token, int userId) {
			super();
			this.id = id;
			this.token = token;
			this.userId = userId;
		}
		private int userId;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
}
