package com.frabbi.londriservice.view.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.application.MyApplication;
import com.frabbi.londriservice.databinding.FragmentCheckoutBinding;
import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.utils.DateAndTime;
import com.frabbi.londriservice.utils.JsonConverter;
import com.frabbi.londriservice.view.adapter.RvAdapterCheckout;
import com.frabbi.londriservice.viewmodel.OrderCheckoutViewModel;
import com.frabbi.londriservice.viewmodel.OrderCheckoutViewModelFactory;

import java.io.Serializable;
import java.util.List;

public class CheckoutFragment extends Fragment {
    private FragmentCheckoutBinding vBinding;
    private List<OrderGettingModel> orderConfirmList;
    private RvAdapterCheckout adapterCheckout;
    private OrderCheckoutViewModel viewModel;
    private OrderList oldOrderList;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false);
        return vBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        OrderCheckoutViewModelFactory factory = new OrderCheckoutViewModelFactory(MyApplication.getRepository());
        viewModel = factory.create(OrderCheckoutViewModel.class);

        oldOrderList = (OrderList) getArguments().getSerializable("orderObject");

        assert getArguments() != null;
        AddressModel address = (AddressModel) getArguments().getSerializable("data");
        if (address != null) {
            vBinding.mapInputTextField.setText(address.getMapLocation().trim());
            vBinding.homeAddressTextField.setText(address.getHomeAddress().trim());
            vBinding.phoneNoIdTextField.setText(address.getPhoneNumber().trim());
        }


        orderConfirmList = (List<OrderGettingModel>) getArguments().getSerializable("orderConfirm");
        if (orderConfirmList != null && !orderConfirmList.isEmpty()) {
            Double gPrice = Double.valueOf(orderConfirmList.get(0).getTotalAmount().toString());
            vBinding.tQty.setText(orderConfirmList.get(0).getTotalQty() + "th");
            vBinding.gPrice.setText(String.valueOf(gPrice));
            vBinding.shippingCharge.setText("60");
            vBinding.discountPercentage.setText("0%");
            vBinding.totalPrice.setText("Total Price: 	৳" + (gPrice + 60));
        }


        //order placed button clicked
        vBinding.orderPlacedButton.setOnClickListener(v -> {
            if (isAddressValidInput() && !orderConfirmList.isEmpty()) {
                AddressModel addressModel = new AddressModel(
                        vBinding.mapInputTextField.getText().toString().trim(),
                        vBinding.homeAddressTextField.getText().toString().trim(),
                        vBinding.phoneNoIdTextField.getText().toString().trim()
                );

                OrderList orderList = new OrderList(
                        JsonConverter.getJsonOrderListString(orderConfirmList),
                        JsonConverter.getGsonAddressString(addressModel),
                        true,
                        DateAndTime.getDateAndTime()
                );
                Bundle bundle = new Bundle();
                assert orderConfirmList != null;
                bundle.putSerializable("orderList", (Serializable) orderList);
                //update data

                if (oldOrderList != null) {
                    Log.i("Id::",""+oldOrderList);
                    orderList.setId(oldOrderList.getId());
                    oldOrderList = orderList;
                    viewModel.update(oldOrderList);
                } else {
                    viewModel.insert(orderList);
                }
                Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_paymentFragment, bundle);

            }
        });

        // edit button click

        vBinding.editButtonId.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("orderObject",oldOrderList);
            bundle.putSerializable("orderConfirm", (Serializable) orderConfirmList);
            Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_location_access_fragment, bundle);
        });

    }

    private boolean isAddressValidInput() {
        String mapInput = vBinding.mapInputTextField.getText().toString().trim();
        String homeAddress = vBinding.homeAddressTextField.getText().toString().trim();
        String phoneNo = vBinding.phoneNoIdTextField.getText().toString().trim();
        return !mapInput.isEmpty() && !homeAddress.isEmpty() && !phoneNo.isEmpty();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterCheckout = new RvAdapterCheckout(this, orderConfirmList);
        vBinding.rvInvoice.setLayoutManager(new LinearLayoutManager(requireContext()));
        vBinding.rvInvoice.setAdapter(adapterCheckout);
    }
}