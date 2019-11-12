package com.example.mvvmandlivedata.until;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {
    @SerializedName("response")
    @Expose
    public News news;

    @Override
    public String toString() {
        return "NewsResponse{" +
                "news=" + news +
                '}';
    }
}
