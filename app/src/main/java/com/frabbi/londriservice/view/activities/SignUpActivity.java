package com.frabbi.londriservice.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding fBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        fBinding.getOtpBtn.setOnClickListener(v -> drivingGetOtpButton());
        fBinding.continueBtnId.setOnClickListener(v ->{
            startActivity(new Intent(this,DashBoardActivity.class));
            finish();
        });
    }

   private void drivingGetOtpButton() {
        new AsyncTask<Void,Integer,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                fBinding.getOtpBtn.setEnabled(false);
                fBinding.getOtpBtn.setTextColor(getResources().getColor(R.color.red));
                fBinding.otpInfo.setVisibility(View.VISIBLE);
                fBinding.insertOtpInputTextId.setVisibility(View.VISIBLE);
                fBinding.continueBtnId.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                fBinding.getOtpBtn.setEnabled(true);
                fBinding.getOtpBtn.setText("Try Again");
                fBinding.getOtpBtn.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 30; i>=0; i--){
                    publishProgress(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                fBinding.getOtpBtn.setText(String.valueOf(values[0]));
            }
        }.execute();
    }

}