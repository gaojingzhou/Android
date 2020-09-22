package com.example.cheers.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.cheers.R;

/**
 * An example full-screen splash activity that hides the system UI (i.e.
 * status bar and navigation/system bar).
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        View mContentView;
        mContentView = findViewById(R.id.imageView);
        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Handler mHideHandler = new Handler();
        mHideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToMainActivity();
            }
        }, 3000);
    }

    private void jumpToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        Log.d("main", "main");
        finish();
    }
}
