package com.gamotrance.OTT.Model;

public enum ContentType {
	IMAGE("IMAGE"),VIDEO("VIDEO"),GIF("GIF"),PROMOTIONAL("VIDEOCALL"),DEFAULT("DEFAULT");
    public String value;
    ContentType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
