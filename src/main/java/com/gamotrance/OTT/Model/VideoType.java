package com.gamotrance.OTT.Model;

public enum VideoType {
	STANDARD("STANDARD"),PREMIUM("PREMIUM"), DIAMOND("DIAMOND"),SINGLEVIDEO("SINGLEVIDEO"),WEBSERIES("WEBSERIES"),MOVIE("MOVIE"),SORTMOVIE("SORTMOVIE"),TRAILER("TRAILER"),UPCOMMING("UPCOMMING"),ENGLISHSHORT("ENGLISHSHORT"),DEFAULT("DEFAULT");
    public String value;
    VideoType(String value)
    {
        this.value=value;
    }
    @Override
	public String toString()
    {
        return value;
    }
}
