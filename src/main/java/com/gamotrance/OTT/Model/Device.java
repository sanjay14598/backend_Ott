package com.gamotrance.OTT.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Device {
	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceName=" + deviceName + ", brand=" + brand + ", deviceModel=" + deviceModel
				+ ", deviceToken=" + deviceToken + ", deviceSdk=" + deviceSdk + ", version=" + version + ", hardware="
				+ hardware + ", buildId=" + buildId + ", loginStatus=" + loginStatus + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    
    private String deviceName;
    private String brand;
    private DeviceType deviceType;
    private String deviceModel;
    private String deviceToken;
   
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	public Device(int id, String deviceName, String brand, DeviceType deviceType, String deviceModel,
			String deviceToken, String deviceSdk, String version, String hardware, String buildId,
			boolean loginStatus) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.brand = brand;
		this.deviceType = deviceType;
		this.deviceModel = deviceModel;
		this.deviceToken = deviceToken;
		this.deviceSdk = deviceSdk;
		this.version = version;
		this.hardware = hardware;
		this.buildId = buildId;
		this.loginStatus = loginStatus;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	private String deviceSdk;
    private String version;
    private String hardware;
    private String buildId;
    private boolean loginStatus;
	public int getId() {
		return id;
	}
	public Device() {
		super();
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceSdk() {
		return deviceSdk;
	}
	public void setDeviceSdk(String deviceSdk) {
		this.deviceSdk = deviceSdk;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
    

}
