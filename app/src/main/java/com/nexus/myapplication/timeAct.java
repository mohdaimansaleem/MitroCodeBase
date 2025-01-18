package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class timeAct extends AppCompatActivity {

    String s="";
    public int src=-1;
    public int dest=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeact);
/*
        setTitle("Timings");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.source1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line, MitroHyd.arr);

        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                src=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });



        AutoCompleteTextView autocomplete2 = (AutoCompleteTextView) findViewById(R.id.destination);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, MitroHyd.arr);

        autocomplete2.setThreshold(1);
        autocomplete2.setAdapter(adapter2);

        autocomplete2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                dest=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });

 */
    }
    }
    /*

    public  void getTime(View view){

        List<ArrayList<String>> time=new ArrayList<>();

        TextView src=findViewById(R.id.source1);
        String source=src.getText().toString();

        TextView dest=findViewById(R.id.destination);
        String destination=dest.getText().toString();



        InputStream is= getResources().openRawResource(R.raw.time);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String referance="";

        int count=0;
        try {

            while(true){
                try {
                    if ((referance= reader.readLine())==null) break;
                    else{
                        String [] broke=referance.split(",");
                        if(broke[0].equals(source) && broke[1].equals(destination)){
                            count++;
                            ArrayList<String> dataAdd=new ArrayList<>();
                            dataAdd.add(broke[2]);
                            dataAdd.add(broke[3]);

                            time.add(new ArrayList<>(dataAdd));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            Context context = getApplicationContext();
            CharSequence text = "SORRY CAN'T LOAD";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        ListView listTime=findViewById(R.id.TimeList);
        TimeAdapter push=new TimeAdapter(this,time);
        listTime.setAdapter(push);

    }

}*/