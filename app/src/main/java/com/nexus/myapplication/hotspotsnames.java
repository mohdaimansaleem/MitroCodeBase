package com.nexus.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class hotspotsnames extends ArrayAdapter {

    ArrayList<String> title;
    Context context;

    public hotspotsnames(@NonNull Context context, ArrayList<String> title) {
        super(context, R.layout.hotspotsview, title);
        this.title = title;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertview, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotspotsview, parent, false);

        String getStr=title.get(position);

        TextView txt = view.findViewById(R.id.textView14);
        txt.setText(getStr);
        return view;
    }

}
