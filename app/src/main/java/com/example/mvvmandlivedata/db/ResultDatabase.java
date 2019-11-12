package com.example.mvvmandlivedata.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmandlivedata.until.FieldsConverter;
import com.example.mvvmandlivedata.until.Result;

import static androidx.room.Room.databaseBuilder;

@Database(entities = Result.class, version = 1)
@TypeConverters({FieldsConverter.class})
public abstract class ResultDatabase extends RoomDatabase{
    public static ResultDatabase instance;

    public abstract ResultDao resultDao();

    public static synchronized ResultDatabase getInstance(Context context) {
            if(instance == null){
                instance = databaseBuilder(context, ResultDatabase.class, "result_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        return instance;

    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;

        public PopulateDbAsyncTask(ResultDatabase resultDatabase) {
            this.resultDao = resultDatabase.resultDao();
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.insert(results[0]);
            return null;
        }
    }


}
