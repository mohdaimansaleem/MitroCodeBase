package com.nexus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class suggestion extends ArrayAdapter {


    List<JSONObject> title;
    Context context;

    public suggestion(@NonNull Context context, List<JSONObject> title) {
        super(context, R.layout.item, title);
        this.title = title;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertview, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.suggestion, parent, false);


        TextView txt = view.findViewById(R.id.textView2);
        JSONObject getStr=title.get(position);
        try {
            txt.setText(getStr.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uri = null;
                try {
                    uri = String.format(Locale.ENGLISH, "geo:%S,%S", getStr.getString("lat"), getStr.getString("long"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);
            }
        });
        return view;
    }


}
