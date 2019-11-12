package com.example.mvvmandlivedata.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmandlivedata.R;
import com.example.mvvmandlivedata.mainactivty.ResultViewModel;
import com.example.mvvmandlivedata.databinding.ResultBinding;
import com.example.mvvmandlivedata.until.Result;

class ResultHolder extends RecyclerView.ViewHolder {

    private ResultBinding mResultBinding;

    public ResultHolder(ResultBinding binding) {
        super(binding.getRoot());
        mResultBinding = binding;
    }
    public ResultHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Result result) {
        int star;
        if(result.isSave()){
           star = R.drawable.starsave;
        }else {
           star = R.drawable.star;
        }
        mResultBinding.setItemViewModel(new ResultListItemViewModel(result,star));
        mResultBinding.executePendingBindings();

    }
    public void saveOrDeleteItem(final Result result, final ResultViewModel viewModel){
        mResultBinding.imageViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.isSave()){
                    mResultBinding.imageViewStar.setImageResource(R.drawable.starsave);
                    result.setSave(true);
                    viewModel.insert(result);

                }
                else {
                    mResultBinding.imageViewStar.setImageResource(R.drawable.star);
                    viewModel.delete(result);
                }
            }
        });

    }

}
