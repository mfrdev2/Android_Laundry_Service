package com.frabbi.londriservice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.frabbi.londriservice.roomdb.repositories.Repository;

import java.util.List;

public class PendingOrderFragmentViewModel extends ViewModel {
    final Repository rp;
    public PendingOrderFragmentViewModel(final Repository rp) {
        this.rp = rp;
    }
   public LiveData<List<OrderList>> getAllPendingList(){
      return rp.getAllPendingList();
    }

    public void deleteItem(OrderList obj){
        rp.delete(obj);
    }

    public void deleteAll(){
        rp.deleteAll();
    }
}
