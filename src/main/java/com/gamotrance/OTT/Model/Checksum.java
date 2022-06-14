package com.gamotrance.OTT.Model;

import java.io.Serializable;

public class Checksum implements Serializable{
	
	String MID;
	String OrderId;
	public Checksum() {
		super();
	}
	public Checksum(String mID, String orderId, String cUST_ID, String iNDUSTRY_TYPE_ID, String cHANNEL_ID,
			String tXN_AMOUNT, String wEBSITE, String cALLBACK_URL, String cHECKSUMHASH) {
		super();
		MID = mID;
		OrderId = orderId;
		CUST_ID = cUST_ID;
		INDUSTRY_TYPE_ID = iNDUSTRY_TYPE_ID;
		CHANNEL_ID = cHANNEL_ID;
		TXN_AMOUNT = tXN_AMOUNT;
		WEBSITE = wEBSITE;
		CALLBACK_URL = cALLBACK_URL;
		CHECKSUMHASH = cHECKSUMHASH;
	}
	String CUST_ID;
	String INDUSTRY_TYPE_ID;
	String CHANNEL_ID;
	String TXN_AMOUNT;
	String WEBSITE;
	//': 'https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=' + OrderId,
	String CALLBACK_URL;
String CHECKSUMHASH;
public String getMID() {
	return MID;
}
public void setMID(String mID) {
	MID = mID;
}
public String getOrderId() {
	return OrderId;
}
public void setOrderId(String orderId) {
	OrderId = orderId;
}
public String getCUST_ID() {
	return CUST_ID;
}
public void setCUST_ID(String cUST_ID) {
	CUST_ID = cUST_ID;
}
public String getINDUSTRY_TYPE_ID() {
	return INDUSTRY_TYPE_ID;
}
public void setINDUSTRY_TYPE_ID(String iNDUSTRY_TYPE_ID) {
	INDUSTRY_TYPE_ID = iNDUSTRY_TYPE_ID;
}
public String getCHANNEL_ID() {
	return CHANNEL_ID;
}
public void setCHANNEL_ID(String cHANNEL_ID) {
	CHANNEL_ID = cHANNEL_ID;
}
public String getTXN_AMOUNT() {
	return TXN_AMOUNT;
}
public void setTXN_AMOUNT(String tXN_AMOUNT) {
	TXN_AMOUNT = tXN_AMOUNT;
}
public String getWEBSITE() {
	return WEBSITE;
}
public void setWEBSITE(String wEBSITE) {
	WEBSITE = wEBSITE;
}
public String getCALLBACK_URL() {
	return CALLBACK_URL;
}
public void setCALLBACK_URL(String cALLBACK_URL) {
	CALLBACK_URL = cALLBACK_URL;
}
public String getCHECKSUMHASH() {
	return CHECKSUMHASH;
}
public void setCHECKSUMHASH(String cHECKSUMHASH) {
	CHECKSUMHASH = cHECKSUMHASH;
}

}
