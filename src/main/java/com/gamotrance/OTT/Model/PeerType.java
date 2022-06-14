package com.gamotrance.OTT.Model;

public enum PeerType {
	REGULAR("REGULAR"),RECOMENDED("RECOMENDED"),ORIGINALS("ORIGINALS"),PRIMIUM("PRIMIUM"),SHOWINDEMAND("SHOWINDEMAND"),HINDIMOVIE("HINDIMOVIE"),MOVIE("MOVIE"),SORTMOVIE("SORTMOVIE"),HINDISORTMOVIE("HINDISORTMOVIE"),TRAILER("TRAILER"),SOUTH("SOUTH"),BESTINBHOJPURI("BESTINBHOJPURI"),TRENDING("TRENDING"),ROMANTIC("ROMANTIC"),YOUTH("YOUTH"),COMEDIES("COMEDIES"),MUSICVIDEO("MUSICVIDEO"),SUPERNATURALS("SUPERNATURALS"),ENGLISHHORROR("ENGLISHHORROR"),ENGLISHEROTIC("ENGLISHEROTIC"),SCIFI("SCIFI"),ADVANTURE("ADVANTURE"),ACTION("ACTION"),HOLLYWOODMOVIE("HOLLYWOODMOVIE"),WEBSERIES("WEBSERIES"),ENGLISHWEBSERIES("ENGLISHWEBSERIES"),ENGLISHSORTMOVIE("ENGLISHSORTMOVIE"),TOP10("TOP10"),HIINDIWEBSERIES("HIINDIWEBSERIES"),ENGLISHMUSIC("ENGLISHMUSIC"),COMMINGSOON("COMMINGSOON"),DEFAULT("DEFAULT");
    public String value;
    PeerType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
