package com.frabbi.londriservice.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.CardSliderViewBinding;
import com.frabbi.londriservice.model.CardSliderModel;

import java.util.List;

public class CardSliderAdapter extends com.github.islamkhsh.CardSliderAdapter<CardSliderAdapter.MyViewHolder> {
    private List<CardSliderModel> list;
    private Context fragment;

    public CardSliderAdapter(List<CardSliderModel> list, Context fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @Override
    public void bindVH(MyViewHolder myViewHolder, int i) {
        final CardSliderModel cardSliderModel =  list.get(i);
        myViewHolder.viewBinding.imgId.setImageResource(cardSliderModel.getImgSrc());
        myViewHolder.viewBinding.imgTitle.setText(cardSliderModel.getImgName());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final CardSliderViewBinding viewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(fragment),
                R.layout.card_slider_view,
                parent,
                false
        );
        return new MyViewHolder(viewBinding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
      final CardSliderViewBinding viewBinding;
        public MyViewHolder(@NonNull CardSliderViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
