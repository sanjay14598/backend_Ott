package com.gamotrance.OTT.Model;

public enum BannerType {
	IMAGE("IMAGE"),VIDEO("VIDEO"),GIF("GIF"),VIDEOCALL("VIDEOCALL"),LIVE("LIVE"),DEFAULT("DEFAULT");
    public String value;
    BannerType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
