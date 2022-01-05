package com.frabbi.londriservice.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.ActivityDashBoardBinding;


import java.util.Objects;

public class DashBoardActivity extends AppCompatActivity {

    private ActivityDashBoardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board);


        setupActionBar();

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        AppBarConfiguration barConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, barConfiguration);
        NavigationUI.setupWithNavController(mBinding.buttonNavMenu, navController);


    }

    private void setupActionBar() {
        //set actionbar as toolbar
        setSupportActionBar(mBinding.toolbar);

        //nav BackButton enable
     //   Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        set listener to nav. backButton
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}