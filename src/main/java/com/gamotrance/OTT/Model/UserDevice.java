package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class UserDevice {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String deviceName;
	private String deviceToken;
	private String deviceImei;
	private boolean deviceStatus;
	private String deviceModel;
	private String deviceDef;
	public UserDevice(int id, @NotNull String deviceName, String deviceToken, String deviceImei, boolean deviceStatus,
			String deviceModel, String deviceDef) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.deviceToken = deviceToken;
		this.deviceImei = deviceImei;
		this.deviceStatus = deviceStatus;
		this.deviceModel = deviceModel;
		this.deviceDef = deviceDef;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getDeviceImei() {
		return deviceImei;
	}
	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}
	public boolean isDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(boolean deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceDef() {
		return deviceDef;
	}
	public void setDeviceDef(String deviceDef) {
		this.deviceDef = deviceDef;
	}
}
