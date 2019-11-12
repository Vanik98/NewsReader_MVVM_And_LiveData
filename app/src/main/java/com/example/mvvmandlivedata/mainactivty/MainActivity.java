package com.example.mvvmandlivedata.mainactivty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.mvvmandlivedata.R;
import com.example.mvvmandlivedata.adapter.ResultAdapter;

import com.example.mvvmandlivedata.until.Result;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager = null ;
    private ResultAdapter adapter;
    private boolean loading ;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Dialog dialog;
    private boolean startDialog = true;

    private List<Result> resultsDb = new ArrayList<>();
    private List<Result> resultRetrofit = new ArrayList<>();
    private List<Result> resultDbAndRetrofit= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        recyclerView = findViewById(R.id.recyclerView);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        getDBResult();
        getRequestResult(false);

        scrollRecyclerView();

        refresRequesth();
    }

    private void setAdapter(){
        if (adapter == null) {
            adapter = new ResultAdapter(resultViewModel);
            adapter.addData(resultDbAndRetrofit);
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    private void getDBResult(){
        resultViewModel.getDbResults().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultsDb.addAll(results);
                resultDbAndRetrofit = filterResultList(resultsDb,resultRetrofit);
                setAdapter();
                }

        });
    }
    private void getRequestResult(final Boolean isScroll){
        resultViewModel.setScroll(isScroll);
        resultViewModel.getRetrofitResults(isScroll).observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultRetrofit.addAll(results);
                resultDbAndRetrofit = filterResultList(resultsDb,resultRetrofit);
                setAdapter();
            }
        });

    }

    private void scrollRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        loading = false;
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        loading = true;
                        break;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                int pastVisiblesItems, visibleItemCount, totalItemCount;
                if(isNetworkAvailable()){
                    if(dy > 0) {
                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false;
                                startDialog = false;
                                getRequestResult(true);
                                startDialog = true;
                            }

                        }
                    }
                }

            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void refresRequesth(){
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.removeAllViews();
                resultDbAndRetrofit.clear();
                getDBResult();
                getRequestResult(false);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private   List<Result> filterResultList(List<Result> resultsDB,List<Result> resultsRequest){
        List<Result> listFilter = new ArrayList<>();

        if(resultsDB.isEmpty() || resultsRequest.isEmpty()){
            listFilter.addAll(resultsRequest);
        }else {
            boolean filter=true;
            for (int j = 0; j < resultsRequest.size(); j++) {
                for (int i = 0; i < resultsDB.size(); i++) {
                    if (resultsRequest.get(j).getId().equals(resultsDB.get(i).getId())) {
                        filter = false;
                        break;
                    }
                }
                if (filter) {
                    listFilter.add(resultsRequest.get(j));
                }
                filter = true;
            }
        }
        resultsDB.addAll(listFilter);
        return resultsDB;
    }

    private void openDialog(Dialog dialog){
        dialog.setContentView(R.layout.dialog_layout);
        dialog.show();
    }
    private void closeDialog(Dialog dialog){

        dialog.dismiss();
    }
}
