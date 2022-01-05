package com.frabbi.londriservice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.roomdb.repositories.Repository;

public class OrderCheckoutViewModel extends ViewModel {
    final Repository repository;
    public OrderCheckoutViewModel(Repository repository) {
        this.repository = repository;
    }
    public void insert(OrderList order){
        repository.insert(order);
    }

    public LiveData<OrderList> findId(Integer id){
        return repository.findId(id);
    }

    public void update(OrderList orderList){
        repository.update(orderList);
    }

}
