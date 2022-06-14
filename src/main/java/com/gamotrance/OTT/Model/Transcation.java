package com.gamotrance.OTT.Model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.SerializedName;
@Document
public class Transcation implements Serializable {

	 private String subType;
	
	   

		public Transcation( String subType, String sTATUS, String cHECKSUMHASH, String bANKNAME, String oRDERID,
			String tXNAMOUNT, String tXNDATE, String mID, String tXNID, String rESPCODE, String pAYMENTMODE,
			String bANKTXNID, String cURRENCY, String gATEWAYNAME, String rESPMSG, String userId) {
		super();
	
		this.subType = subType;
		STATUS = sTATUS;
		CHECKSUMHASH = cHECKSUMHASH;
		BANKNAME = bANKNAME;
		ORDERID = oRDERID;
		TXNAMOUNT = tXNAMOUNT;
		TXNDATE = tXNDATE;
		MID = mID;
		TXNID = tXNID;
		RESPCODE = rESPCODE;
		PAYMENTMODE = pAYMENTMODE;
		BANKTXNID = bANKTXNID;
		CURRENCY = cURRENCY;
		GATEWAYNAME = gATEWAYNAME;
		RESPMSG = rESPMSG;
		this.userId = userId;
	}

		public Transcation() {
			super();
		}

		public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

		

		public String getSTATUS() {
			return STATUS;
		}

		public void setSTATUS(String sTATUS) {
			STATUS = sTATUS;
		}

		public String getCHECKSUMHASH() {
			return CHECKSUMHASH;
		}

		public void setCHECKSUMHASH(String cHECKSUMHASH) {
			CHECKSUMHASH = cHECKSUMHASH;
		}

		public String getBANKNAME() {
			return BANKNAME;
		}

		public void setBANKNAME(String bANKNAME) {
			BANKNAME = bANKNAME;
		}

		public String getORDERID() {
			return ORDERID;
		}

		public void setORDERID(String oRDERID) {
			ORDERID = oRDERID;
		}

		public String getTXNAMOUNT() {
			return TXNAMOUNT;
		}

		public void setTXNAMOUNT(String tXNAMOUNT) {
			TXNAMOUNT = tXNAMOUNT;
		}

		public String getTXNDATE() {
			return TXNDATE;
		}

		public void setTXNDATE(String tXNDATE) {
			TXNDATE = tXNDATE;
		}

		public String getMID() {
			return MID;
		}

		public void setMID(String mID) {
			MID = mID;
		}

		public String getTXNID() {
			return TXNID;
		}

		public void setTXNID(String tXNID) {
			TXNID = tXNID;
		}

		public String getRESPCODE() {
			return RESPCODE;
		}

		public void setRESPCODE(String rESPCODE) {
			RESPCODE = rESPCODE;
		}

		public String getPAYMENTMODE() {
			return PAYMENTMODE;
		}

		public void setPAYMENTMODE(String pAYMENTMODE) {
			PAYMENTMODE = pAYMENTMODE;
		}

		public String getBANKTXNID() {
			return BANKTXNID;
		}

		public void setBANKTXNID(String bANKTXNID) {
			BANKTXNID = bANKTXNID;
		}

		public String getCURRENCY() {
			return CURRENCY;
		}

		public void setCURRENCY(String cURRENCY) {
			CURRENCY = cURRENCY;
		}

		public String getGATEWAYNAME() {
			return GATEWAYNAME;
		}

		public void setGATEWAYNAME(String gATEWAYNAME) {
			GATEWAYNAME = gATEWAYNAME;
		}

		public String getRESPMSG() {
			return RESPMSG;
		}

		public void setRESPMSG(String rESPMSG) {
			RESPMSG = rESPMSG;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@SerializedName("status")
		private String STATUS;
		@SerializedName("checksumhash")
	    private String CHECKSUMHASH;
		@SerializedName("bankname")
	    private String BANKNAME;
		@SerializedName("orderid")
	    private String ORDERID;
		@SerializedName("txnamount")
	    private String TXNAMOUNT;
		@SerializedName("txndate")
	    private String TXNDATE;
		@SerializedName("mid")
	    private String MID;
	  @SerializedName("txnid")
	    private String TXNID;
	  @SerializedName("rescode")
	    private String RESPCODE;
	  @SerializedName("paymentmode")
	    private String PAYMENTMODE;
	    @SerializedName("banktxnid")
	    private String BANKTXNID;
	    @SerializedName("currency")
	    private String CURRENCY;
	    @SerializedName("gatewayname")
	    private String GATEWAYNAME;
	    @SerializedName("respmsg")
	    private String RESPMSG;
	    @SerializedName("userId")
	    private String userId;
	    public Transcation( String subType, String sTATUS, String cHECKSUMHASH, String bANKNAME, String oRDERID,
				String tXNAMOUNT, String tXNDATE, String mID, String tXNID, String rESPCODE, String pAYMENTMODE,
				String bANKTXNID, String cURRENCY, String gATEWAYNAME, String rESPMSG, String userId, String vdoId) {
			super();
			
			this.subType = subType;
			STATUS = sTATUS;
			CHECKSUMHASH = cHECKSUMHASH;
			BANKNAME = bANKNAME;
			ORDERID = oRDERID;
			TXNAMOUNT = tXNAMOUNT;
			TXNDATE = tXNDATE;
			MID = mID;
			TXNID = tXNID;
			RESPCODE = rESPCODE;
			PAYMENTMODE = pAYMENTMODE;
			BANKTXNID = bANKTXNID;
			CURRENCY = cURRENCY;
			GATEWAYNAME = gATEWAYNAME;
			RESPMSG = rESPMSG;
			this.userId = userId;
			this.vdoId = vdoId;
		}

	

		public String getVdoId() {
			return vdoId;
		}

		public void setVdoId(String vdoId) {
			this.vdoId = vdoId;
		}

		@SerializedName("vdoId")
	    private String vdoId;
	    @Override
	    public String toString()
	    {
	        return new StringBuffer()
	        .append("[STATUS]-")
	        .append(STATUS)
	        .append(" TXNAMOUNT:")
	        .append(TXNAMOUNT)
	        .append(" userId:")
	        .append(userId)
	        .append(" BANKTXNID: ")
	        .append(BANKTXNID).toString();
	    }
}
