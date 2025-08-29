package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nexus.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PriceCalci extends AppCompatActivity {

    String sourceStation = "";
    String destinationStation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_calci);
        setTitle("Ticket Price");

        MobileAds.initialize(this, initializationStatus -> {});
        AdView mAdView = findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());

        AutoCompleteTextView sourceInput = findViewById(R.id.source1);
        AutoCompleteTextView destInput = findViewById(R.id.destination);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Home.arr);
        sourceInput.setAdapter(adapter);
        destInput.setAdapter(adapter);

        sourceInput.setOnItemClickListener((parent, view, position, id) -> {
            sourceStation = parent.getItemAtPosition(position).toString().trim();
        });

        destInput.setOnItemClickListener((parent, view, position, id) -> {
            destinationStation = parent.getItemAtPosition(position).toString().trim();
        });
    }

    public void getPrice(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {}

        if (sourceStation.isEmpty() || destinationStation.isEmpty()) {
            resetUI("Prices shown here give a rough idea of the cost of metro tickets.", "", "");
            return;
        }

        if (sourceStation.equals(destinationStation)) {
            resetUI("", "0", "YOU ARE ALREADY AT THE DESTINATION");
            return;
        }

        new FareApiTask().execute(sourceStation, destinationStation);
    }

    private void resetUI(String info, String fare, String offer) {
        ((TextView) findViewById(R.id.textView10)).setText("YOUR FARE:");
        ((TextView) findViewById(R.id.textView5)).setText(info);
        ((TextView) findViewById(R.id.textView11)).setText(fare);
        ((TextView) findViewById(R.id.textView13)).setText(offer);
    }

    private class FareApiTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String from = params[0];
            String to = params[1];
            try {
                URL url = new URL("https://ltmetro.com/wp-content/themes/hello-theme-child-master/fair-details.php?");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                String payload = "from_station=" + from + "&to_station_name=" + to;
                OutputStream os = conn.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
                os.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString().trim();
            } catch (Exception e) {
                return "ERROR";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ERROR")) {
                Toast.makeText(getApplicationContext(), "Failed to fetch fare", Toast.LENGTH_SHORT).show();
                resetUI("", "", "");
            } else {
                resetUI("", result + " Rs", "(UPI transactions have a 10% discount)");
            }
        }
    }
}