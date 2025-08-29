package com.nexus.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;

import com.nexus.myapplication.R;
import com.nexus.myapplication.adapters.HeroAdapter;
import com.nexus.myapplication.databinding.ActivityMain2Binding;
import com.nexus.myapplication.timeAct;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ActivityMain2Binding binding;
    public static String arr[]={
            "Miyapur R00",
            "JNTU College R01",
            "KPHB Colony R02",
            "Kukatpally R03",
            "Balanagar R04",
            "Moosapet R05",
            "Bharatnagar R06",
            "Erragadda R07",
            "ESI Hospital R08",
            "SR Nagar R09",
            "Ameerpet R10",
            "Punjagutta R11",
            "Irrum Manzil R12",
            "Khairatabad R13",
            "Lakdi Ka Pul R14",
            "Assembly R15",
            "Nampally R16",
            "Gandhi Bhavan R17",
            "Osmania Medical College R18",
            "MG Bus station R19",
            "Malakpet R20",
            "New Market R21",
            "Musarambagh R22",
            "Dilsukhnagar R23",
            "Chaitanyapuri R24",
            "Victoria Memorial R25",
            "LB Nagar R26",
            "Nagole",
            "Uppal",
            "Stadium B29",
            "NGRI B30",
            "Habsiguda B31",
            "Tarnaka B32",
            "Mettuguda B33",
            "Secunderabad East B34",
            "JBParade Ground B35",
            "Paradise B36",
            "Rasoolpura B37",
            "Prakash Nagar B38",
            "Begumpet B39",
            "Madhura Nagar B40",
            "Yousufguda B41",
            "Jubilee Hills Road No 5  B42",
            "Jubilee Hills Check Post B43",
            "Peddamma Gudi B44",
            "Madhapur B45",
            "Durgam Cheruvu B46",
            "Hitec City B47",
            "Raidurg B48",
            "Secunderabad West G49",
            "Gandhi Hospital G50",
            "Musheerabad G51",
            "RTC X Roads G52",
            "Chikkadpally G53",
            "Narayanguda G54",
            "Sultan Bazaar G55"
    };

    int src=-1;
    int dest=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        ViewPager2 heroViewPager = findViewById(R.id.heroViewPager);
        HeroAdapter adapter = new HeroAdapter(Arrays.asList(
                R.drawable.hero4,
                R.drawable.hero2,
                R.drawable.hero3
        ));
        heroViewPager.setAdapter(adapter);

// Auto-scroll logic
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int currentPage = 0;

            @Override
            public void run() {
                if (currentPage >= adapter.getItemCount()) {
                    currentPage = 0;
                }
                heroViewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000); // scroll every 3 seconds
            }
        };
        handler.post(runnable);


    }


    public void map(View view){
        Intent i=new Intent(this, Map.class);
        startActivity(i);
    }

    public void hotspots(View view){
        Intent i=new Intent(this, Hotspot.class);
        startActivity(i);
    }


    public void devs(View view){
        Intent i=new Intent(this, DevTeam.class);
        startActivity(i);
    }

    public void rateApp(View view)
    {
        String url = "https://play.google.com/store/apps/details?id=com.nexus.myapplication";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public void share(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey download this app at play store https://play.google.com/store/apps/details?id=");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void payments(View view){
        Intent i=new Intent(this, Payment.class);
        startActivity(i);
    }

    public void parking(View view){
        Intent i=new Intent(this, Parking.class);
        startActivity(i);
    }
    public void contact(View view){
        Intent i=new Intent(this, Contact.class);
        startActivity(i);
    }
    public void about(View view){
        Intent i=new Intent(this, AboutUs.class);
        startActivity(i);
    }
    public void time(View view){
        Intent i=new Intent(this, timeAct.class);
        startActivity(i);
    }
    public void stations(View view){
        Intent i=new Intent(this, MetroStation.class);
        startActivity(i);
    }
    public void priceCalci(View view){
        Intent i=new Intent(this, PriceCalci.class);
        startActivity(i);
    }
    public void feedback(View view){
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSfTaYCf5IMJoowAhUgTgnHmYe3lbeS93aNZINidi04NASMB-g/viewform?usp=sf_link";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void guide(View view){
        Intent i=new Intent(this, Travel.class);
        startActivity(i);
    }
    public void nearest(View view){
        Intent i=new Intent(this, NearestMetro.class);
        startActivity(i);
    }
    public void findDest(View view){

        String s;
        AutoCompleteTextView autocomplete1 = (AutoCompleteTextView) findViewById(R.id.source1);
        s=autocomplete1.getText().toString();

        if(s.length()>3)
            src=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
        else return;

        AutoCompleteTextView autocomplete2 = (AutoCompleteTextView) findViewById(R.id.destination);
        s=autocomplete2.getText().toString();
        if(s.length()>3)
            dest=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
        else return;

        if(src!=-1 && dest!=-1 && src!=dest) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add(Integer.toString(src));
            ans.add(Integer.toString(dest));

            Intent i = new Intent(this, MapList.class);
            i.putStringArrayListExtra("list", ans);
            startActivity(i);
        }
        else if(src==dest && src!=-1){
            Context context = getApplicationContext();
            CharSequence text = "YOU ARE ALREADY AT THE LOCATION";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "PLEASE ENTER THE REQUIRED FIELDS";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        src=-1;
        dest=-1;
        autocomplete1.setText("");
        autocomplete2.setText("");
    }

}