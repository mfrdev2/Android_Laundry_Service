package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentStatusBinding;
import com.frabbi.londriservice.view.activities.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {
   private FragmentStatusBinding vBinding;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_status, container, false);
        return vBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        prepareViewpager();

    }

    private void prepareViewpager() {
        ArrayList<String> tabTitle = new ArrayList<>();
        tabTitle.add("ACTIVE");
        tabTitle.add("PENDING");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ActiveOrderSectionFragment());
        fragments.add(new PendingOrderSectionFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(),fragments,tabTitle);
        vBinding.viewPager.setAdapter(adapter);
        vBinding.tabLayout.setupWithViewPager(vBinding.viewPager,true);
    }

 /*   private void setRecyclerView() {
        vBinding.rvStatusOderList.setLayoutManager(new LinearLayoutManager(getContext()));
       vBinding.rvStatusOderList.setAdapter(new RvAdapterStatusOrderList(this));
    }*/

    @Override
    public void onResume() {
        super.onResume();

    }
}