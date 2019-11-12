package com.example.mvvmandlivedata.adapter;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.mvvmandlivedata.R;

import com.example.mvvmandlivedata.mainactivty.ResultViewModel;
import com.example.mvvmandlivedata.databinding.ResultBinding;
import com.example.mvvmandlivedata.until.Result;

import java.util.List;

public class ResultAdapter extends ListAdapter<Result, ResultHolder>{

    private List<Result> resultsList;
    private static  final int VIEW_TYPE_ITEM = 0;
    private static  final int VIEW_TYPE_LOADING = 1;
    private ResultViewModel viewModel;
 
    public ResultAdapter(ResultViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.viewModel = viewModel;
    }

    private static final DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId().equals(newItem.getId()) && oldItem.getApiUrl().equals(newItem.getApiUrl());

        }
    };

    public void addData(List<Result> resultsList){
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(viewType==VIEW_TYPE_ITEM){
            ResultBinding binding =  ResultBinding.inflate(inflater,parent,false);
            return  new ResultHolder(binding);
        } else {
            View view = inflater.inflate(R.layout.layout_progresbar, parent, false);
            return  new ResultHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        int getViewType = holder.getItemViewType();
        if(getViewType==VIEW_TYPE_ITEM) {
            Result result = resultsList.get(position);
            holder.bind(result);
            holder.saveOrDeleteItem(result,viewModel);
        }
    }

    @Override
    public int getItemCount() {
        if (resultsList.size()!=0)
        return resultsList.size()+1;
        else return resultsList.size();
    }

    @Override
    public int getItemViewType(int position) {
            return position == resultsList.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

}
