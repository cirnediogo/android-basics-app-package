package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Diogo on 09/06/2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocationOffset;
    private String mPrimaryLocation;
    private Long mDate;
    private String mUrl;

    public Earthquake(double magnitude, String location, Long date, String url) {
        this.mMagnitude = magnitude;
        int ofIndex = location.indexOf("of");
        if (ofIndex < 0) {
            this.mLocationOffset = "Near the";
            this.mPrimaryLocation = location;
        } else {
            this.mLocationOffset = location.substring(0, ofIndex + 2);
            this.mPrimaryLocation = location.substring(ofIndex + 3);
        }
        this.mDate = date;
        this.mUrl = url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getMagnitudeString() {
        return QueryUtils.DECIMAL_FORMATTER.format(mMagnitude);
    }

    public String getLocationOffset() {
        return mLocationOffset;
    }

    public String getPrimaryLocation() {
        return mPrimaryLocation;
    }

    public Long getDate() {
        return mDate;
    }

    public String getDateString() {
        return QueryUtils.DATE_FORMATTER.format(new Date(mDate));
    }

    public String getTimeString() {
        return QueryUtils.TIME_FORMATTER.format(new Date(mDate));
    }

    public String getUrl() {
        return mUrl;
    }

}
