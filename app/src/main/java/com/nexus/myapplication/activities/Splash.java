package com.nexus.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nexus.myapplication.R;

public class Splash extends AppCompatActivity {

    private ImageView logoImage;
    private TextView appNameText;
    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoImage = findViewById(R.id.logoImage);
        appNameText = findViewById(R.id.appNameText);
        loadingBar = findViewById(R.id.loadingBar);

        Animation scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation fadeAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        logoImage.setVisibility(View.VISIBLE);
        logoImage.startAnimation(scaleAnim);

        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                appNameText.setVisibility(View.VISIBLE);
                appNameText.startAnimation(fadeAnim);
                loadingBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splash.this, Home.class);
            startActivity(intent);
            finish();
        }, 2500); // Adjust duration as needed
    }
}
