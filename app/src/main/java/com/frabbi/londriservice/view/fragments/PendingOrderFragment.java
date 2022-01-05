package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.application.MyApplication;
import com.frabbi.londriservice.databinding.FragmentPendingOrderBinding;
import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.view.adapter.RvAdapterPendingOrderList;
import com.frabbi.londriservice.viewmodel.PendingOrderFragmentViewModel;
import com.frabbi.londriservice.viewmodel.PendingOrderFragmentViewModelFactory;

import java.util.List;


public class PendingOrderFragment extends Fragment {
    private FragmentPendingOrderBinding vB;
    private RvAdapterPendingOrderList pAdapter;
    private PendingOrderFragmentViewModel viewModel;
    public PendingOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vB = DataBindingUtil.inflate(inflater, R.layout.fragment_pending_order, container, false);
        return vB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        PendingOrderFragmentViewModelFactory factory = new PendingOrderFragmentViewModelFactory(MyApplication.getRepository());
        viewModel = factory.create(PendingOrderFragmentViewModel.class);
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        pAdapter = new RvAdapterPendingOrderList(getContext(),viewModel);
        viewModel.getAllPendingList().observe(requireActivity(), list ->{
                pAdapter.setList(list);
                pAdapter.notifyDataSetChanged();
        });
        vB.rvPendingList.setLayoutManager(new LinearLayoutManager(requireContext()));
        vB.rvPendingList.setAdapter(pAdapter);
    }
}