package com.frabbi.londriservice.repositories;

import androidx.lifecycle.MutableLiveData;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.model.OrderCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    private static List<OrderCategoryModel> list;

    public MutableLiveData<List<OrderCategoryModel>> getCategoryList() {
        dataReady();
        MutableLiveData<List<OrderCategoryModel>> data = new MutableLiveData<>();
        data.setValue(
                list
        );
        return data;
    }

    private static void dataReady() {
    list = new ArrayList<>();
        list.add(new OrderCategoryModel(R.drawable.laundry,"Wash+Iron"));
        list.add(new OrderCategoryModel(R.drawable.laundry,"Only Wash"));
        list.add(new OrderCategoryModel(R.drawable.laundry,"Iron"));
    }
}
