package com.example.android.miwok;

/**
 * Created by Diogo on 30/05/2017.
 */

public class Word {

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageSource;
    private int mMediaSource;

    public Word() {
        this.mMiwokTranslation = "";
        this.mDefaultTranslation = "";
        this.mImageSource = 0;
    }

    public Word(String defaultTranslation, String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageSource = 0;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageSource) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageSource = imageSource;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageSource, int mediaSource) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageSource = imageSource;
        this.mMediaSource = mediaSource;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageSource() { return mImageSource; }

    public int getMediaSource() { return mMediaSource; }

}
