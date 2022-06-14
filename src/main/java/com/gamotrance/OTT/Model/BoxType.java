package com.gamotrance.OTT.Model;

public enum BoxType {
	H("H"),V("V"),BIGV("BIGV"),BIGNWATCH("BIGNWATCH"),ROGINALS("ROGINALS"),CUSTOM("CUSTOM"),ROUND("ROUND"),SLIDER("SLIDER"),FETURED("FETURED"),STATUS("STATUS"),DEFAULT("DEFAULT");
    public String value;
    BoxType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
