package com.example.mvvmandlivedata.mainactivty;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.mvvmandlivedata.db.ResultDao;
import com.example.mvvmandlivedata.db.ResultDatabase;
import com.example.mvvmandlivedata.retrofit.MyRetrofit;
import com.example.mvvmandlivedata.until.Result;

import java.util.List;

public class ResultRepository {
    private ResultDao resultDao;
    private LiveData<List<Result>> mResultsDB;
    private MyRetrofit myRetrofit;
    public ResultRepository(Application application) {
        resultDao = ResultDatabase.getInstance(application).resultDao();
        mResultsDB = resultDao.getDbResults();
        myRetrofit = MyRetrofit.getInstance();
    }

    public void insert(Result result) {
        new InsertNoteAsyncTask(resultDao).execute(result);
    }
    public void update(Result result) {
        new UpdateNoteAsyncTask(resultDao).execute(result);
    }
    public void delete(Result result) {
        new DeleteNoteAsyncTask(resultDao).execute(result);
    }
    public void deleteAllResult() {
        new DeleteAllResultAsyncTask(resultDao).execute();
    }

    public LiveData<List<Result>> getDbResults() {
        return mResultsDB;
    }

    public LiveData<List<Result>> getRetrofitResults(Boolean isScroll) {
        return myRetrofit.getRetrofitResults(isScroll);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;
        private InsertNoteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }
        @Override
        protected Void doInBackground(Result... notes) {
            resultDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;
        private UpdateNoteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }
        @Override
        protected Void doInBackground(Result... results){
            resultDao.update(results[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;

        private DeleteNoteAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.delete(results[0]);
            return null;
        }
    }
    private static class DeleteAllResultAsyncTask extends AsyncTask<Void, Void, Void> {
        private ResultDao resultDao;
        private DeleteAllResultAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            resultDao.deleteAllNotes();
            return null;
}
    }



}
