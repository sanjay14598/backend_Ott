package com.gamotrance.OTT.Model;

public enum PaymentActiveStatus {
	ACTIVE("ACTIVE"),INACTIVE("INACTIVE");
    public String value;
    PaymentActiveStatus(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
