package com.example.ghostnight.ikenassignment.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ghostnight.ikenassignment.R;
import com.example.ghostnight.ikenassignment.ui.fragment.MainFragment;
import com.example.ghostnight.ikenassignment.ui.fragment.SplashFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fragment_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MainFragment.newInstance()).commit();
            }
        },3000);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, SplashFragment.newInstance()).commit();
    }
}
