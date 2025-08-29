package com.nexus.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nexus.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class TimeAdapter extends ArrayAdapter {

    private List<ArrayList<String>> itemList;
    private Context context;


    public TimeAdapter(Context context, List<ArrayList<String>> itemList) {
        super(context, R.layout.timeformate, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.timeformate, parent, false);
        }

        ArrayList<String> currentItem = itemList.get(position);

        TextView t1 = (TextView) convertView.findViewById(R.id.TextArrival);
        t1.setText(currentItem.get(0));

        TextView t2 = (TextView) convertView.findViewById(R.id.TextDept);
        t2.setText(currentItem.get(1));

        TextView t3 = (TextView) convertView.findViewById(R.id.TextNo);
        t3.setText(Integer.toString(position+1));
        return convertView;
    }

}
