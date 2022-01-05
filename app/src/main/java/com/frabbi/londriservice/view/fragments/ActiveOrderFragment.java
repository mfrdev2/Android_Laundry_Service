package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentActiveOrderBinding;
import com.frabbi.londriservice.view.adapter.RvAdapterActiveOrderList;

public class ActiveOrderFragment extends Fragment {
    private FragmentActiveOrderBinding vB;
   private RvAdapterActiveOrderList adapter;


    public ActiveOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vB= DataBindingUtil.inflate(inflater, R.layout.fragment_active_order, container, false);
        return vB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        adapter = new RvAdapterActiveOrderList(this);
        vB.rvOrderActive.setLayoutManager(new LinearLayoutManager(getContext()));
        vB.rvOrderActive.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}