package com.frabbi.londriservice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frabbi.londriservice.model.OrderGettingModel;

import java.util.List;

public class OrderConfirmViewModel extends ViewModel {
    private MutableLiveData<List<OrderGettingModel>> orderList = new MutableLiveData<>();

    public LiveData<List<OrderGettingModel>> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderGettingModel> orderList) {
        this.orderList.setValue(orderList);
    }
}
