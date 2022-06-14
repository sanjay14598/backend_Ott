package com.gamotrance.OTT.Model;

public enum UserType {
	BASIC("BASIC"),STANDARD("STANDARD"),PRIMIUM("PRIMIUM"),
	DIAMOND("DIAMOND"),ONEYEAR("ONEYEAR"),SIXMONTHS("SIXMONTHS"),
	THREEMONTHS("THREEMONTHS"),ONEMONTH("ONEMONTH");
    public String value;
    UserType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
