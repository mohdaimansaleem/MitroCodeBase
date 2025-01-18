package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class PriceCalci extends AppCompatActivity {

    String s="";
    public int src=-1;
    public int dest=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_calci);
        setTitle("Ticket Price");

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

    }

    public  void getPrice(View view){

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            return;
        }
        if(src==-1 || dest==-1){
            TextView text1=(TextView) findViewById(R.id.textView10);
            text1.setText("");

            TextView text2=(TextView) findViewById(R.id.textView5);
            text2.setText("Prices shown here give a rough idea of the cost of metro tickets.");

            TextView tx3=(TextView) findViewById(R.id.textView11);
            tx3.setText("");

            TextView TextOffer=(TextView) findViewById(R.id.textView13);
            TextOffer.setText("");

            return;
        }
        if(src==dest){
            TextView text1=(TextView) findViewById(R.id.textView10);
            text1.setText("YOUR FARE:");

            TextView text2=(TextView) findViewById(R.id.textView5);
            text2.setText("");

            TextView tx3=(TextView) findViewById(R.id.textView11);
            tx3.setText("0");

            TextView TextOffer=(TextView) findViewById(R.id.textView13);
            TextOffer.setText("YOU ARE ALREADY AT THE DESTINATION");

            return;
        }


        InputStream is= getResources().openRawResource(R.raw.fare);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String referance="";
        ArrayList<String> priceLine=new ArrayList<>();

        try {
            int count=0;
            while(true){
                try {
                    if (!((referance= reader.readLine())!=null)) break;
                    else{
                        if(count==src)
                            priceLine.add(referance);
                        count++;
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
        String[] fare=priceLine.get(0).split(",");

        TextView tx3=(TextView) findViewById(R.id.textView11);
        tx3.setText(fare[dest]+" Rs");

        TextView TextOffer=(TextView) findViewById(R.id.textView13);
        TextOffer.setText("(Upi transactions have a 10% discount)");

        TextView text=(TextView) findViewById(R.id.textView10);
        text.setText("YOUR FARE:");

        TextView text2=(TextView) findViewById(R.id.textView5);
        text2.setText("");
    }
}