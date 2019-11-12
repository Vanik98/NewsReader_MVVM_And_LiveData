package com.example.mvvmandlivedata.until;

import androidx.room.Entity;
import androidx.room.TypeConverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public Fields(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
