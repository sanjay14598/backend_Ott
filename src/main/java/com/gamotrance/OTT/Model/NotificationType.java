package com.gamotrance.OTT.Model;

public enum NotificationType {
GROUP("GROUP"),INDIVIDUAL("INDIVIDUAL");
public String value;
NotificationType(String value)
{
    this.value=value;
}
@Override
public String toString()
{
    return value;
}

}
