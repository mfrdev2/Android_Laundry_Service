package com.frabbi.londriservice.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frabbi.londriservice.model.OrderCategoryModel;
import com.frabbi.londriservice.repositories.Repository;

import java.util.List;

public class MyViewBind extends ViewModel {
    private MutableLiveData<List<OrderCategoryModel>>  list = new MutableLiveData<>();

    public void initializeListData(){
        list = Repository.getInstance().getCategoryList();
    }
    public LiveData<List<OrderCategoryModel>> getList() {
        return list;
    }

}
