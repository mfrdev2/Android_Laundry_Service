package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentHomeBinding;
import com.frabbi.londriservice.model.CardSliderModel;
import com.frabbi.londriservice.view.adapter.CardSliderAdapter;
import com.google.android.gms.common.util.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
private FragmentHomeBinding homeBinding;
private static List<CardSliderModel> cardSliderModelList;
private CardSliderAdapter cardSliderAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);

        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        cardSliderModelList = new ArrayList<>();
        cardSliderModelList.add(new CardSliderModel(R.drawable.temp1,"temp1"));
        cardSliderModelList.add(new CardSliderModel(R.drawable.temp2,"temp2"));
        cardSliderModelList.add(new CardSliderModel(R.drawable.temp3,"temp3"));

        cardSliderAdapter = new CardSliderAdapter(cardSliderModelList,getContext());

        homeBinding.viewPager.setAdapter(cardSliderAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}