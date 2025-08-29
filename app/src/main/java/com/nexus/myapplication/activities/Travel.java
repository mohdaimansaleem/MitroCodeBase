package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nexus.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Travel extends AppCompatActivity implements LocationListener {

    public double lat;
    public double lon;

    public  ArrayList<JSONObject> amk=new ArrayList<>();
    private static  final int REQUEST_LOCATION=1;

    String s="";
    public int src=-1;


    String[] sher={
            "uppal stadium",
            "Forum Sujana Mall","Ruchi's Fast Food",
            "Idl lake", " Meridian Restaurant","GVK One Mall",
            "City Center Mall", "Prasads IMAX ","Lumbini Park",
            "Telangana State Assembly", "Birla Mandir",
            "Nampally Railway Station", "Yousufain Darga","PVR Irrum Manzil",
            "Nizam's Museum", "MJ Market",
            "Osmania Medical College", "Mecca Masjid",
            "Mahatma Gandhi Bus Station (MGBS)", "Salar Jung Museum","Purani Haveli","Charminar","Chowmalla Palace",
            "Ramoji Film City","Wonderla",
            "Nagole Lake","Regus-Hyderabad","CSIR - Indian Institute Of Chemical Technology","Saroor Nagar Lake","Parade Ground","Jubilee Bus Stand","Jalavihar","Madhura Nagra Park",
            "Check Post","Yousufguda Police Ground","TV 5 News","Jubilee Hills Urban Park","KBR Park","Monolith Park","Inorbit Mall","Golconda Fort",
            "Durgam Cheruvu Street Lake Park","Durgam Cheruvu Walk Way","Retro Drive In","Ram ki Bandi","Cyber Towers","Ocean park","Gandhi Hospital",
            "Bawarchi Restaurant","The Waterfront ","NTR Gardens","Taj Banjara","Tank Bund","GravityZip",
            "Peshawar Restaurant","Hotel Shadab","Hotel Nayab"
    };



    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        setTitle("Travel Guide");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LocationManager lm = (LocationManager) getSystemService(Context. LOCATION_SERVICE ) ;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager. GPS_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager. NETWORK_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        if (!gps_enabled && !network_enabled) {
            try {
                new AlertDialog.Builder(Travel. this )
                        .setMessage( "Please on the GPS location for accurate results" )
                        .setPositiveButton( "Settings" , new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                        startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                                    }
                                })
                        .setNegativeButton( "Cancel" , null )
                        .show() ;
            }
            catch (Exception e){
                return;
            }

        }

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);

        if (!(ActivityCompat.checkSelfPermission(Travel.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Travel.this,

                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(Travel.this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,Travel.this);

        }

        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.bolo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line, sher);

        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
            }
        });


        Button bt;
        bt=findViewById(R.id.route);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hotspot.HotspotsK();

                if(s.equals(""))return;
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                   return;
                }


                int ans=0;
                for(int i=0;i<57;i++)
                {
                    if(Hotspot.hotspotMap.get(i).contains(s))
                    {
                        ans=i;
                        break;
                    }
                }

                CardView c=findViewById(R.id.cardg);
                c.setVisibility(View.VISIBLE);
                TextView t=findViewById(R.id.textView34);

                t.setText((Home.arr[ans]));

                if(amk.size()>0){
                    TextView tc=findViewById(R.id.textView32);
                    try {
                        tc.setText(amk.get(0).getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }




    @Override
    public void onLocationChanged(Location location){

        lat=location.getLatitude();
        lon=location.getLongitude();


        locationManager.removeUpdates(Travel.this);



        String  as = "{\"students\": [\n" +
                "        {\"name\":\"Miyapur\",\"lat\":\"17.49655511109275\",\"long\":\" 78.37307189762151\"},\n" +//17.49655511109275, 78.37307189762151
                "        {\"name\":\"JNTU College\",\"lat\":\"17.498670208430948\",\"long\":\"78.38893113314487\"},\n" +//17.498670208430948, 78.38893113314487
                "        {\"name\":\"KPHB Colony\",\"lat\":\"17.49378236216134\",\"long\":\"78.40173298807238\"},\n" +//17.49378236216134, 78.40173298807238
                "        {\"name\":\"Kukatpally\",\"lat\":\"17.485082592739392\",\"long\":\"78.41168135444356\"},\n" +//17.485082592739392, 78.41168135444356
                "        {\"name\":\"Balanagar\",\"lat\":\"17.47676597356499\",\"long\":\"78.4219867898636\"},\n" +//17.47676597356499, 78.4219867898636
                "        {\"name\":\"Moosapet\",\"lat\":\"17.471989153259134\",\"long\":\"78.42602648993935\"},\n" +//17.471989153259134, 78.42602648993935
                "        {\"name\":\"Bharatnagar\",\"lat\":\"17.46413256408599\",\"long\":\"78.43002429737989\"},\n" +//17.46413256408599, 78.43002429737989
                "        {\"name\":\"Erragadda\",\"lat\":\"17.45721269777435\",\"long\":\"78.43352157670269\"},\n" +//17.45721269777435, 78.43352157670269
                "        {\"name\":\"ESI Hospital\",\"lat\":\"17.447477297679228\",\"long\":\"78.43830065697351\"},\n" +//17.447477297679228, 78.43830065697351
                "        {\"name\":\"SR Nagar\",\"lat\":\"17.441533625162286\",\"long\":\"78.44170293061751\"},\n" +//17.441533625162286, 78.44170293061751
                "        {\"name\":\"Ameerpet\",\"lat\":\"435709636021766\",\"long\":\"78.44460037050558\"},\n" +//17.435709636021766, 78.44460037050558
                "        {\"name\":\"Punjagutta\",\"lat\":\"17.42861343105057\",\"long\":\"78.45112995089679\"},\n" +//17.42861343105057, 78.45112995089679
                "        {\"name\":\"Irrum Manzil\",\"lat\":\"17.420379244210235\",\"long\":\"78.45619371368828\"},\n" +//17.420379244210235, 78.45619371368828
                "        {\"name\":\"Khairatabad\",\"lat\":\"17.41278226775719\",\"long\":\"78.46022112924064\"},\n" +//17.41278226775719, 78.46022112924064
                "        {\"name\":\"Lakdi Ka Pul\",\"lat\":\"17.403945168650772\",\"long\":\"78.465023116027\"},\n" +//17.403945168650772, 78.465023116027
                "        {\"name\":\"Assembly\",\"lat\":\"17.398087057078136\",\"long\":\"78.47084194829877\"},\n" +//17.398087057078136, 78.47084194829877
                "        {\"name\":\"Nampally\",\"lat\":\"17.392305698128233\",\"long\":\"78.47016021630274\"},\n" +//17.392305698128233, 78.47016021630274
                "        {\"name\":\"Gandhi Bhavan\",\"lat\":\"17.38605084632505\",\"long\":\"78.47307901951888\"},\n" +//17.38605084632505, 78.47307901951888
                "        {\"name\":\"Osmania Medical College\",\"lat\":\"17.382501200690267\",\"long\":\"78.48096754911207\"},\n" +//17.382501200690267, 78.48096754911207
                "        {\"name\":\"MG Bus station\",\"lat\":\"17.379863981851102\",\"long\":\"78.48627256202619\"},\n" +//17.379863981851102, 78.48627256202619
                "        {\"name\":\"Malakpet\",\"lat\":\"17.37722062340575\",\"long\":\"78.49384007633525\"},\n" +//17.37722062340575, 78.49384007633525
                "        {\"name\":\"New Market\",\"lat\":\"17.37345384801486\",\"long\":\"78.50315566791329\"},\n" +//17.37345384801486, 78.50315566791329
                "        {\"name\":\"Musarambagh\",\"lat\":\"17.37114821469516\",\"long\":\"78.51194092367234\"},\n" +//17.37114821469516, 78.51194092367234
                "        {\"name\":\"Dilsukhnagar\",\"lat\":\"17.36853625028612\",\"long\":\"78.52570499520266\"},\n" +//17.36853625028612, 78.52570499520266

                "        {\"name\":\"Chaitanyapuri\",\"lat\":\"17.368376235951946\",\"long\":\"78.53582492468544\"},\n" +//17.368376235951946, 78.53582492468544
                "        {\"name\":\"Victoria Memorial\",\"lat\":\"17.361577111806195\",\"long\":\"78.54400321223602\"},\n" +//17.361577111806195, 78.54400321223602
                "        {\"name\":\"LB Nagar\",\"lat\":\"17.34972733982562\",\"long\":\"78.54796991601411\"},\n" +//17.34972733982562, 78.54796991601411
                "        {\"name\":\"Secunderabad West\",\"lat\":\"17.433798554150144\",\"long\":\"78.4994324097276\"},\n" +//17.433798554150144, 78.4994324097276
                "        {\"name\":\"Gandhi Hospital\",\"lat\":\"17.425666887905194\",\"long\":\"78.5018725327531\"},\n" +//17.425666887905194, 78.5018725327531
                "        {\"name\":\"Musheerabad\",\"lat\":\"17.417793366173584\",\"long\":\"78.49935120889586\"},\n" +//17.417793366173584, 78.49935120889586
                "        {\"name\":\"RTC X Roads\",\"lat\":\"17.40710734659368\",\"long\":\"78.4965579493953\"},\n" +//17.40710734659368, 78.4965579493953
                "        {\"name\":\"Chikkadpally\",\"lat\":\"17.400791095141827\",\"long\":\"78.49487613757178\"},\n" +//17.400791095141827, 78.49487613757178
                "        {\"name\":\"Narayanguda\",\"lat\":\"17.394235060702645\",\"long\":\"78.48987522905361\"},\n" +//17.394235060702645, 78.48987522905361
                "        {\"name\":\"Sultan Bazaar\",\"lat\":\"17.384119814935463\",\"long\":\"78.48369867402019\"},\n" +//17.384119814935463, 78.48369867402019
                "        {\"name\":\"Nagole\",\"lat\":\"17.391095546525325\",\"long\":\"78.55878567258733\"},\n" +
                "        {\"name\":\"uppal\",\"lat\":\"17.40008549150384\",\"long\":\"78.56017146762339\"},\n" +
                "        {\"name\":\"Stadium\",\"lat\":\"17.401184852130356\",\"long\":\"78.55425072599144\"},\n" +
                "        {\"name\":\"NGRI\",\"lat\":\"17.40758891260213\",\"long\":\"78.55974913823059\"},\n" +
                "        {\"name\":\"Habsiguda\",\"lat\":\" 17.421111054123692\",\"long\":\"78.54037961124457\"},\n" +
                "        {\"name\":\"Tarnaka\",\"lat\":\"17.42871448065143\",\"long\":\"78.528728733998\"},\n" +
                "        {\"name\":\"Mettuguda\",\"lat\":\"17.436365610421365\",\"long\":\"78.51974406827392\"},\n" +
                "        {\"name\":\"Secunderabad East\",\"lat\":\"17.43778900025487\",\"long\":\"78.50443604550568\"},\n" +
                "        {\"name\":\"Parade Ground\",\"lat\":\"17.44377353016131\",\"long\":\"78.49685120689112\"},\n" +
                "        {\"name\":\"Paradise\",\"lat\":\"17.44432104384098\",\"long\":\"78.48644964500588\"},\n" +
                "        {\"name\":\"Rasoolpura\",\"lat\":\"17.44453846814573\",\"long\":\"78.47640786827556\"},\n" +
                "        {\"name\":\"Prakash Nagar\",\"lat\":\"17.445874102714658\",\"long\":\"78.46575072223763\"},\n" +
                "        {\"name\":\"Begumpet\",\"lat\":\"17.43834055704569\",\"long\":\"78.45689846827429\"},\n" +
                "        {\"name\":\"Madhura Nagar\",\"lat\":\"17.43654616592323\",\"long\":\"78.43992685292824\"},\n" +
                "        {\"name\":\"Yousufguda\",\"lat\":\"17.435770961181614\",\"long\":\"78.42765822093567\"},\n" +
                "        {\"name\":\"Jubilee Hills Road No 5\",\"lat\":\"17.430935583331202\",\"long\":\"78.42315802173349\"},\n" +
                "        {\"name\":\"Jubilee Hills Check Post\",\"lat\":\"17.428233852061474\",\"long\":\" 78.41372203328201\"},\n" +//17.428233852061474, 78.41372203328201
                "        {\"name\":\"Peddamma Gudi\",\"lat\":\"17.431368957408598\",\"long\":\"78.40952383657861\"},\n" +
                "        {\"name\":\"Madhapur\",\"lat\":\"17.43742330157167\",\"long\":\"78.4008388989663\"},\n" +
                "        {\"name\":\"Durgam Cheruvu\",\"lat\":\"17.443628446957756\",\"long\":\"78.38767106827538\"},\n" +
                "        {\"name\":\"Hitec City\",\"lat\":\"17.450088920499304\",\"long\":\"78.38333382966091\"},\n" +
                "        {\"name\":\"Raidurg\",\"lat\":\"17.443063017779718\",\"long\":\"78.37706902659357\"}]}";//17.44212121191986, 78.37715387315575

        try {
            JSONObject jsonObject = new JSONObject(as);
            JSONArray jsonArray=jsonObject.getJSONArray("students");


            int mindist=Integer.MAX_VALUE;

            for(int i=0;i<jsonArray.length();i++){

                JSONObject obj=jsonArray.getJSONObject(i);

                double a=Double.parseDouble(obj.getString("lat"));
                double b=Double.parseDouble(obj.getString("long"));

                int dist=(int)distance(lat,lon,a,b);

                if(dist<=mindist){
                    mindist=dist;
                }

            }


            amk=new ArrayList<>();

            for(int i=0;i<jsonArray.length();i++){

                JSONObject obj=jsonArray.getJSONObject(i);

                double a=Double.parseDouble(obj.getString("lat"));
                double b=Double.parseDouble(obj.getString("long"));


                int dist=(int)distance(lat,lon,a,b);


                if(dist==mindist){
                    amk.add(obj);

                }


            }
            Log.i("size",amk.size()+"");
            TextView tc=findViewById(R.id.textView32);
            tc.setText(amk.get(0).getString("name"));
        }
        catch (Exception e){

        }
    }

    @Override
    public void onStatusChanged(String provider,int status,Bundle extras){}

    public  double distance(double lat1, double lon1, double lat2, double lon2) {
        double EARTH_RADIUS = 3958.75;; // in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance*10;
    }


}