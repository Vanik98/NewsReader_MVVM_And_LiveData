package com.example.mvvmandlivedata.adapter;

import com.example.mvvmandlivedata.until.Result;

public class ResultListItemViewModel {
    private String mSection;
    private String mTitle;
    private String mTime;
    private int mStar;
    private String mImageUrl;

    public ResultListItemViewModel(Result result,int star) {
        mSection = result.getSectionName();
        mTitle = result.getWebTitle();
        mTime = result.getWebPublicationDate();
        mImageUrl = result.getFields().getThumbnail();
        mStar = star;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public int getStar() {
        return mStar;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

}
