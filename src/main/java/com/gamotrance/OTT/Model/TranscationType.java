package com.gamotrance.OTT.Model;

public enum TranscationType {
	REFARAL("REFARAL"),REALADDED("REALADDED"),REWARD("REWARD"),DEFAULT("DEFAULT");
    public String value;
    TranscationType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
