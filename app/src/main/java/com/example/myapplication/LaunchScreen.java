package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityLaunchScreenBinding;

public class LaunchScreen extends Activity {
    private final int LAUNCH_DISPLAY_LENGTH = 3000;

    private TextView textView;
    private ActivityLaunchScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        binding = ActivityLaunchScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sp = getSharedPreferences("hasVisited",
                            Context.MODE_PRIVATE);
                    boolean hasVisited = sp.getBoolean("hasVisited",false);
                    if (!hasVisited) {
                        SharedPreferences.Editor e = sp.edit();
                        e.putBoolean("hasVisited", true);
                        e.commit();
                        Intent mainIntent = new Intent(LaunchScreen.this, MainActivity.class);
                        LaunchScreen.this.startActivity(mainIntent);
                        LaunchScreen.this.finish();
                    } else {
                        Intent mainIntent = new Intent(LaunchScreen.this, MainActivity.class);
                        LaunchScreen.this.startActivity(mainIntent);
                        LaunchScreen.this.finish();
                    }
                }
            },LAUNCH_DISPLAY_LENGTH);
        }
    }
}
