package com.gamotrance.OTT.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AppUpdate {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
  
    public AppUpdate() {
		super();
	}
	public AppUpdate(int id, String appUpdateUrl, String updateDate, String updateVersion, String updateStatus,
			String updateMessage) {
		super();
		this.id = id;
		this.appUpdateUrl = appUpdateUrl;
		this.updateDate = updateDate;
		this.updateVersion = updateVersion;
		this.updateStatus = updateStatus;
		this.updateMessage = updateMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppUpdateUrl() {
		return appUpdateUrl;
	}
	public void setAppUpdateUrl(String appUpdateUrl) {
		this.appUpdateUrl = appUpdateUrl;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateVersion() {
		return updateVersion;
	}
	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getUpdateMessage() {
		return updateMessage;
	}
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}
	private String appUpdateUrl;
    private String  updateDate;
    private String updateVersion;
    private String updateStatus;
    private String updateMessage;

}
