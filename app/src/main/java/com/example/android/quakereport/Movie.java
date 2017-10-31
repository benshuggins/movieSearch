package com.example.android.quakereport;

import java.io.Serializable;

/**
 * Created by simranjain1507 on 08/02/17.
 */
public class Movie implements Serializable {

    private String voteAverage;
    private String place;
    private String overView;
    private String poster;
    private String releaseDate;



    public Movie(String GvoteAverage,String Gplace, String Gposter, String GoverView, String GreleaseDate)
    {
        voteAverage = GvoteAverage;
        poster = Gposter;
        place=Gplace;
        overView= GoverView;
        releaseDate = GreleaseDate;


    }

    public String getVoteAverage()
    {
        return voteAverage;
    }

    public String getPlace()
    {
        return place;
    }

    public String getPoster() {return  poster;}

    public String getoverView() {return overView;}

    public String getReleaseDate() {return releaseDate;}




}