package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentActiveOrderSectionBinding;


public class ActiveOrderSectionFragment extends Fragment {
    private FragmentActiveOrderSectionBinding vBiding;

    public ActiveOrderSectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vBiding= DataBindingUtil.inflate(inflater,R.layout.fragment_active_order_section, container, false);
        return vBiding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}