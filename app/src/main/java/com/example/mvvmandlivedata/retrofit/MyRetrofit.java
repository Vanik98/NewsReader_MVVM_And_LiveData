package com.example.mvvmandlivedata.retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmandlivedata.until.NewsResponse;
import com.example.mvvmandlivedata.until.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRetrofit {
    private ApiInterface apiInterface;
    private Call<NewsResponse> call;
    private final static String API_KEY = "d47b5885-f0d3-4367-a1d6-728aaf4353cb";
    private final static int PAGE_SIZE = 10;
    private final static String SHOW_FILDS ="thumbnail";
    private final static long AUTO_REFRESH_TIME = 30000;
    private int page = 1;
    private int maxPageSize;
    private List<Result> results = new ArrayList<>();
    private MyRetrofit() {
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
    }
    private static MyRetrofit instance;
    public  static MyRetrofit getInstance(){
        if(instance == null){
            instance  = new MyRetrofit();
        }
        return instance;
    }
    public LiveData<List<Result>> getRetrofitResults(Boolean isScroll){
        if(isScroll){
            page++;
        }else {
            page = 1;
            results.clear();
        }
        final MutableLiveData<List<Result>> resultData = new MutableLiveData<>();

        call = apiInterface.getNews(API_KEY,PAGE_SIZE,page,SHOW_FILDS);

        call.clone().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                results.addAll(response.body().news.getResults());
                resultData.setValue(results);
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
        return resultData;
    }
}
