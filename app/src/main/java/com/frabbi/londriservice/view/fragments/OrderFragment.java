package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentOrderBinding;
import com.frabbi.londriservice.view.adapter.RvAdapterOrderCategory;
import com.frabbi.londriservice.viewmodel.MyViewBind;

public class OrderFragment extends Fragment {
    private FragmentOrderBinding mBinding;
    private MyViewBind myViewBind;
    private RvAdapterOrderCategory rvAdapter;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_order, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myViewBind = ViewModelProviders.of(this).get(MyViewBind.class);
        rvAdapter = new RvAdapterOrderCategory(this);
        mBinding.rvList.setLayoutManager(new GridLayoutManager(requireContext(),2));
        myViewBind.initializeListData();
        myViewBind.getList().observe(requireActivity(), orderCategories -> {
           if(orderCategories != null){
               if(!orderCategories.isEmpty()){

                   mBinding.rvList.setVisibility(View.VISIBLE);
                   mBinding.tvEmptyTitle.setVisibility(View.GONE);
                   rvAdapter.setList(orderCategories);
               }else {
                   mBinding.rvList.setVisibility(View.GONE);
                   mBinding.tvEmptyTitle.setVisibility(View.VISIBLE);
               }
           }
       });

       mBinding.rvList.setAdapter(rvAdapter);
    }
}