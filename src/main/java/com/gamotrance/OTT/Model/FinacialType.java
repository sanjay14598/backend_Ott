package com.gamotrance.OTT.Model;

public enum FinacialType {
	DEBIT("DEBIT"),CREDIT("CREDIT"),DEFAULT("DEFAULT");
    public String value;
    FinacialType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
