package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentOrderDetailsBinding;


public class OrderDetailsFragment extends Fragment {
    private FragmentOrderDetailsBinding vBinding;

    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigate(R.id.action_orderDetailsFragment_to_activeOrderFragment);
            }

        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_order_details, container, false);
        return vBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            vBinding.orderTrackButton.setOnClickListener(v->{
                Navigation.findNavController(v).navigate(R.id.action_orderDetailsFragment_to_orderTrackerFragment);
            });
    }
}