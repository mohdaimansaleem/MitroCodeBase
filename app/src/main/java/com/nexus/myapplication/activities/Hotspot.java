package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nexus.myapplication.R;
import com.nexus.myapplication.hotspotsnames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Hotspot extends AppCompatActivity {

    public static HashMap<Integer,ArrayList<String>> hotspotMap =new HashMap<>();
    public static ArrayList<String> exampleList = new ArrayList<>();
    String s="";
    public int val=-1;
    public static void HotspotsK()
    {

        exampleList.addAll(Arrays.asList("Hotel Sitara Grand Miyapur", "Angaara", "Mayuri Restaurant","Beijing Bistro","Pranaam hospital"));
        hotspotMap.put(0,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("DMart", "Forum Sujana Mall","Ruchi's Fast Food","KFC","Mehfil Restaurant","Landmark Hospital"));
        hotspotMap.put(1,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList( "KLM Fashion Mall", "South India Shopping Mall","Ramdev Rao Hospital","Chaitanya Food Court","Platform 65 The Train Restaurant","Hotel Mayukha Jungle Theme Restaurant"));
        hotspotMap.put(2,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList( "Chennai Shopping Mall", "Pranaam Supermarket", "Tabla Restaurant","Omni Hospital","Idl lake"));
        hotspotMap.put(3,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("KIMS Hospital", "Ratnadeep Supermarket-Balanagar","Image Hospitals","Shoppers Stop"));
        hotspotMap.put(4,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Moosapet Market", "Andhra Pradesh State Road Transport Corporation (APSRTC) bus depot","Arabian Mandi","Vasundhara Hospital"));
        hotspotMap.put(5,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Foodies Restaurant", "Pride of Hyderabad","Southern Spice Express","Hotel Devi Grand"));
        hotspotMap.put(6,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList( "Melli Restaurant","Red Spice Family Restaurant","Neelima Hospitals","Surabhi family restaurant","Rayudu Biryani House","Madinath-al-mandi"));
        hotspotMap.put(7,new ArrayList<>(exampleList));;
        exampleList.clear();

        exampleList.addAll(Arrays.asList( "ESI Hospital" ,"Pumega Hospital"));
        hotspotMap.put(8,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Moghul’s Paradiez Restaurant", "Smokey Street","Mad Eats","South India Shopping mall","Universal Kababs and Biryani Restaurant","State TB Center", "Pumega Hospital"));
        hotspotMap.put(9,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("South India Shopping Mall", "Spencers Plaza","Kritunga Restaurant","Pizza Hut","KFC","Aster Prime Hospital"));
        hotspotMap.put(10,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Hyderabad Central", " Meridian Restaurant","Fishland Restaurant","GVK One Mall","Chutneys","Nizam's Institute Of Medical Sciences(NIMS)"));
        hotspotMap.put(11,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Pizzetto","PVR Irrum Manzil","Tea Lounge At The Arcade","City Center Mall", "Prasads IMAX ","Lumbini Park"));
        hotspotMap.put(12,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Darjeeling Fusion Momo","The Waterfront ", "Astoria Restaurant "," Kshetra Vegetarian Restaurant","NTR Gardens","Taj Banjara","Tank Bund"));
        hotspotMap.put(13,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Shahi Dastarkhwan Restaurant", "Chicha's Restaurant-Lakdikapul","Shah Ghouse Restaurant","Cafe Niloufer","Peshawar Restaurant"));
        hotspotMap.put(14,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Telangana State Assembly","Haj House","Telangana State Archaeology Museum","Birla Mandir","Public Gardens","Townhouse 1190 The Grand Plaza", "Cafe Bahar Restaurant"));
        hotspotMap.put(15,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Nampally Railway Station", "Yousufain Darga","Alhamdulillah Hotel","Pista House ","Angara 5","Subhan Bakery"));
        hotspotMap.put(16,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Nizam's Museum", "MJ Market","Hotel Aadaab","Karachi Bakery","Ram Ki Bandi"));
        hotspotMap.put(17,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Osmania Medical College","Koti Bus Stop","VV Function Hall"));
        hotspotMap.put(18,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Mahatma Gandhi Bus Station (MGBS)", "Salar Jung Museum","Purani Haveli","Charminar","Chowmalla Palace","Mecca Masjid","Hotel Shadab","Hotel Nayab"));
        hotspotMap.put(19,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Sohail Hotel", "Waves Restaurant","Yashoda Hospitals"));
        hotspotMap.put(20,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Hotel Akshaya", "New Market"));
        hotspotMap.put(21,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Mataam Al Turki", "Grand Bawarchi"));
        hotspotMap.put(22,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Platform 65 The Train Restaurant ", "Gismath Jail Mandi ","Dilsukhnagar Public Library","KFC"));
        hotspotMap.put(23,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("aibhave Restaurant & Banquet Hall", "Chaitanyapuri Bus Station","Green Leaves Hotel","Raghava Hospital"));
        hotspotMap.put(24,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Paradise Biryani ", "Barbeque Nation ","Princeton Convention Center"));
        hotspotMap.put(25,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Cafe Coffee day ", "Abinand Grand Hotels","Ramoji Film City","Wonderla"));
        hotspotMap.put(26,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Nagole Lake","Cafe Coffee day ", "Svm Grand ","LIFAFA Cafe and Bistro","KFC","Pantaloons","Supraja Hospitals"));
        hotspotMap.put(27,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Uppal Depot","uppal stadium","Natural Ice-Cream","Red Bucket Biryani","Majestic Arabian Mandi","Spark Hospitals","Gravity’s Grand"));
        hotspotMap.put(28,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Rajiv Gandhi International Stadium", "DSL Virtue Mall","South India Shopping Mall"));
        hotspotMap.put(29,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("The Nosh Bistro","TRENDS","Eagle's Pizza","Regus-Hyderabad","Sreenidhi Bawarchi"," NGRI Dispensary"));
        hotspotMap.put(30,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("FRYKIKI","CSIR - Indian Institute Of Chemical Technology","Saroor Nagar Lake","Baskin Robins","Matrix Hospital","Pasand Multi Cuisine Restaurant"));
        hotspotMap.put(31,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Lallaguda Loco Shed","Kumbakarnas Restaurant","Suraksha Children’s Hospital","Golden Peacock Chinese Restaurant"));
        hotspotMap.put(32,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Street Byte","Mettuguda Bus Stop","Mettuguda Metro Parking","Chopsticks Restaurant","Kim Fung The Golden Peacock Chinese Restaurant"));
        hotspotMap.put(33,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Kasturi Hospital & IVF Centre Telangana","Dmart Secundrabad","Wesley Degree College","Apollo Hospitals Secundrabad"));
        hotspotMap.put(34,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Clock Tower","Ohris Eatmor Secundrabad","Time Square Hotel","Parade Ground","Lazeez Restaurant","Picket Park","Jubilee Bus Stand"));
        hotspotMap.put(35,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Paradise Biryani","Rollsking Paradise","Street Pizza Paradise","Barbeque Nation","Contonment Park"));
        hotspotMap.put(36,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("The Belgiun waffle Co.","Hyderabad Metro Rail Limited","Chutneys-Rasoolpura","Gappe Vappe"));
        hotspotMap.put(37,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Al Arabian Island Mataam Al Mandi","Chiraan Fort Club","Sanjeevaiah Children Park","PACE Hospitals","Vantillu Multi Cuisine Restaurant"));
        hotspotMap.put(38,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Medicover Hospital","Country Club Begumpet","High Five Sky Launge and Pub","The Momo House","Jalavihar","Ohri’s Jiva Imperia","CGHS Hospitals"));
        hotspotMap.put(39,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Krishna Kanth Park","Pondy Parottas","Madhura Nagra Park","Birthday Party Place"));
        hotspotMap.put(40,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Sri Kotla Vijay Bhaskar Stadium","Mega Tasty N Spicy Biryani House","Mahmood Paradise Function Hall","Check Post","Yousufguda Police Ground"));
        hotspotMap.put(41,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Pizza Hut-Jubileehills","SAGE Farm Cafe","Hangout","TV 5 News"));
        hotspotMap.put(42,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Farzi Cafe","Air Live Jubilee Hills","Jubilee Hills Urban Park","KBR Park"));
        hotspotMap.put(43,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("The Hole In The Wall Cafe","Mandi@36 Arabian Kitchen","Monolith Park"));
        hotspotMap.put(44,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("State Gallery Of Art","F House","AB's-Absolute Barbeque","Repete Brewery & Kitchen","Inorbit Mall","Golconda Fort"));
        hotspotMap.put(45,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Durgam Cheruvu Street Lake Park","Durgam Cheruvu Walk Way","Aazebo Restaurant","Retro Drive In"));
        hotspotMap.put(46,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Minerva Coffee","Ram ki Bandi","Haldiram's","Cyber Towers","Karachi Bakery"));
        hotspotMap.put(47,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("New York Slice Pizza","Waffle House","STR8UP Club","Citrus Cafe","Slounge","Ocean park","Slounge","GravityZip"));
        hotspotMap.put(48,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Alpha Hotel","Hotel Sitara-SEC-WEST","Clock Tower","Parcel Office","Kims Hospitals","Yashoda Hospitals","CMR Shopping Mall","Mayabazar Restaurant"));
        hotspotMap.put(49,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Sattva Necklace Mall","MNK Vittal Central Court","Gandhi Hospital","Alfatha Hotel", "Sarvottam Restaurant"));
        hotspotMap.put(50,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Kashish Convention","Sanjeevaiah Park","Guru Nanak Care Hospitals","Sahara Banquet Hall"," Golden Crown Restaurant"));
        hotspotMap.put(51,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Bawarchi Restaurant","Indira Park","State Central Library"));
        hotspotMap.put(52,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("HRD Degree College","Chikkadpally Bus Stop","Karachi Bakery"));
        hotspotMap.put(53,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Marvel Convention Centre","Sunrise Super Speciality Children Hospitals","Taj Mahal Hotel","Mehfil Restaurant"));
        hotspotMap.put(54,new ArrayList<>(exampleList));
        exampleList.clear();

        exampleList.addAll(Arrays.asList("Plantation Park","Hyderabad Metro Rail Office","Govt ENT Hospital","Troop Bazar","Santosh Dhaba Exclusive"," Mcdonald’s","KFC"));
        hotspotMap.put(55,new ArrayList<>(exampleList));
        exampleList.clear();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspots);
        setTitle("Hotspots");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.cream));

        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.destination);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line, Home.arr);

        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                val=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });
    }

    public void gethotspots(View view){

        if(val==-1)return;

        HotspotsK();

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
           return;
        }

        ListView ls=findViewById(R.id.list_item2);
        ArrayList<String> am= hotspotMap.get(val);

        hotspotsnames myadpt=new hotspotsnames(this,am);
        ls.setAdapter(myadpt);
    }
}