package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.nexus.myapplication.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setTitle("Contact");
        setupHyperlink();
    }

    private void setupHyperlink() {
        TextView feedback = findViewById(R.id.feedback);
        feedback.setMovementMethod(LinkMovementMethod.getInstance());
        TextView queries = findViewById(R.id.queries);
        queries.setMovementMethod(LinkMovementMethod.getInstance());
        TextView contact_us = findViewById(R.id.contact_us);
        contact_us.setMovementMethod(LinkMovementMethod.getInstance());
    }
}