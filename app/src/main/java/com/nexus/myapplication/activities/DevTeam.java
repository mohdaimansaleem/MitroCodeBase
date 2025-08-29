package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.nexus.myapplication.R;

public class DevTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        setTitle("About Developers");
        setupHyperlink();
    }

    private void setupHyperlink() {
        TextView aiman = findViewById(R.id.aiman);
        TextView shahriyaar = findViewById(R.id.shahriyaar);
        TextView kaleem = findViewById(R.id.kaleem);
        TextView rayyan = findViewById(R.id.rayyan);

        aiman.setMovementMethod(LinkMovementMethod.getInstance());
        shahriyaar.setMovementMethod(LinkMovementMethod.getInstance());
        kaleem.setMovementMethod(LinkMovementMethod.getInstance());
        rayyan.setMovementMethod(LinkMovementMethod.getInstance());
    }
}