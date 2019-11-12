package com.example.mvvmandlivedata.retrofit;

import com.example.mvvmandlivedata.until.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String get="search" ;

    @GET(get)
    Call<NewsResponse> getNews(@Query("api-key") String apiKey, @Query("page-size") int page_size, @Query("page") int page, @Query("show-fields") String show_fields);

}
