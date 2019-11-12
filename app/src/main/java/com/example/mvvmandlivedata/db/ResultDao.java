package com.example.mvvmandlivedata.db;

import android.app.Notification;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmandlivedata.until.Result;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

@Dao
public interface ResultDao {
    @Insert
    void insert(Result result);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);
    @Query("DELETE FROM result_table")
    void deleteAllNotes();
    @Query("SELECT * FROM result_table ORDER BY id DESC") // * - means, that we want all columns
    LiveData<List<Result>> getDbResults();


}
