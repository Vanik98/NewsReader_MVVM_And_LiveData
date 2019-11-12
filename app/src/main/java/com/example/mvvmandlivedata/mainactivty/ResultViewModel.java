package com.example.mvvmandlivedata.mainactivty;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.mvvmandlivedata.until.Result;

import java.util.List;

public class ResultViewModel  extends AndroidViewModel {
    private ResultRepository repository;
    private LiveData<List<Result>> resultsDb;
    private LiveData<List<Result>> resultRetrofit;
    private Boolean isScroll;


    public ResultViewModel(@NonNull Application application) {
        super(application);
        repository = new ResultRepository(application);
        resultsDb = repository.getDbResults();
    }
    public void insert(Result result){
    repository.insert(result);
    }
    public void update(Result result){
        repository.update(result);
    }
    public void delete(Result result) {
        repository.delete(result);
    }
    public void delteAllResults(){
        repository.deleteAllResult();
    }


    public LiveData<List<Result>> getDbResults() {
        return repository.getDbResults();
    }
    public LiveData<List<Result>> getRetrofitResults(Boolean isScroll) {
        return repository.getRetrofitResults(isScroll);
    }

    public void setScroll(Boolean scroll) {
        isScroll = scroll;
        resultRetrofit = repository.getRetrofitResults(isScroll);
    }
}
