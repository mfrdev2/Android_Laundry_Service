package com.frabbi.londriservice.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.frabbi.londriservice.roomdb.repositories.Repository;

public class PendingOrderFragmentViewModelFactory implements ViewModelProvider.Factory {
    final Repository repository;
    public PendingOrderFragmentViewModelFactory(final Repository repository) {
                this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PendingOrderFragmentViewModel.class)) {
            return (T) new PendingOrderFragmentViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown viewModel class");
    }

}
