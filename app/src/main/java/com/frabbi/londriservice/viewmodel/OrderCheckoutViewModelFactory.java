package com.frabbi.londriservice.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.frabbi.londriservice.roomdb.repositories.Repository;

public class OrderCheckoutViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;
    public OrderCheckoutViewModelFactory(final Repository repository){
        this.repository  = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OrderCheckoutViewModel.class)) {
            return (T) new OrderCheckoutViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown viewModel class");
    }
}
