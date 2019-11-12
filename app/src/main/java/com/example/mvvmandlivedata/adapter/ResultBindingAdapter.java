package com.example.mvvmandlivedata.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ResultBindingAdapter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView,String urlImage){
        Glide.with(imageView.getContext()).load(urlImage).into(imageView);
    }
    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView,int resource){
        imageView.setImageResource(resource);
    }

}
