package com.gamotrance.OTT.Model;

public enum CoinRedemiType {
	LIVE("LIVE"),STICKER("STICKER"),PERSONALM("PERSONALM"),VIDEOCALL("VIDEOCALL"),REQUEST("REQUEST"),DEFAULT("DEFAULT");
    public String value;
    CoinRedemiType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
