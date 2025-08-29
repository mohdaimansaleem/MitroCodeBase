package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nexus.myapplication.R;
import com.nexus.myapplication.adapters.Myadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetroStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_stations);
        setTitle("Metro Stations");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ArrayList<String> change=new ArrayList<>();

        String[] backup = Arrays.copyOf(Home.arr, Home.arr.length);
        Arrays.sort(backup);

        ListView ls=findViewById(R.id.list);
        List ans = Arrays.asList(backup);

        Myadapter myadpt=new Myadapter( this,ans,change);
        ls.setAdapter(myadpt);
    }
}