package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentPaymentBinding;
import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.utils.JsonConverter;

import java.util.List;


public class PaymentFragment extends Fragment {
    private FragmentPaymentBinding vBinding;

    public PaymentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_payment, container, false);
        return vBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        OrderList data = (OrderList) getArguments().getSerializable("orderList");
        List<OrderGettingModel> orderList = JsonConverter.getJsonOrderListObject(data.getPOrderList());
        AddressModel addressModel = JsonConverter.getGsonAddressObject(data.getPAddress());

        Log.i("OrderList", "onViewCreated:"+orderList);
        Log.i("TAG", "onViewCreated: "+addressModel);
    }
}