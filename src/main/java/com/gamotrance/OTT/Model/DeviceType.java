package com.gamotrance.OTT.Model;

public enum DeviceType {
	SUPERDEVICE("REGULAR"),SUBDEVICE("PREMIUM"),DEFAULT("DEFAULT");
    public String value;
    DeviceType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
