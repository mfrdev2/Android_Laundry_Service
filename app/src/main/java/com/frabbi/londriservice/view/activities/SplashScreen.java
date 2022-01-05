package com.frabbi.londriservice.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.SplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
 private SplashScreenBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = SplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.spalash_screen_anim);
        mBinding.welcomeTextId.setAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_for_image);
        mBinding.imageView.setAnimation(animation2);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, SignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}