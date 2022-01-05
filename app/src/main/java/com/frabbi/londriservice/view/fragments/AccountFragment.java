package com.frabbi.londriservice.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {
private FragmentAccountBinding mBinding;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
            inflater.inflate(R.menu.account_profile_menu_item,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                drivingEditMenu(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void drivingEditMenu(Boolean isEditButtonEnable) {
        if(isEditButtonEnable){
            mBinding.floatingActionButton.setVisibility(View.VISIBLE);
            mBinding.pUpdateBtn.setVisibility(View.VISIBLE);
            mBinding.pName.setEnabled(true);
            mBinding.pAddress.setEnabled(true);
            mBinding.pEmail.setEnabled(true);
        }else {
            mBinding.floatingActionButton.setVisibility(View.GONE);
            mBinding.pUpdateBtn.setVisibility(View.GONE);
            mBinding.pName.setEnabled(false);
            mBinding.pAddress.setEnabled(false);
            mBinding.pEmail.setEnabled(false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBinding.pUpdateBtn.setOnClickListener(v-> drivingEditMenu(false));
    }


}