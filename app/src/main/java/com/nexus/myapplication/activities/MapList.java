package com.nexus.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nexus.myapplication.R;
import com.nexus.myapplication.adapters.Myadapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;



public class MapList extends AppCompatActivity {
    public Graph gh;
    ArrayList<String> change=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);
        setTitle("Mitro");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

         AdView mAdView;
         mAdView = findViewById(R.id.adView);
         AdRequest adRequest = new AdRequest.Builder().build();
         mAdView.loadAd(adRequest);

        gh=new Graph();
        gh.buildGraph();
        findWay();
    }
    public void findWay(){

        ArrayList<String> dataFromHome=getIntent().getExtras().getStringArrayList("list");

        int src=Integer.parseInt(dataFromHome.get(0));
        int dest=Integer.parseInt(dataFromHome.get(1));

        ArrayList<String> shortestPathlist=gh.shortestPath(src,dest);

        ListView pathList=findViewById(R.id.list_item);

        for(int i=1;i<shortestPathlist.size()-1;i++){

            String presStation=shortestPathlist.get(i);

            int prevLen=shortestPathlist.get(i-1).length();
            char prevColor=shortestPathlist.get(i-1).charAt(prevLen-3);

            int nextLen=shortestPathlist.get(i+1).length();
            char nextColor=shortestPathlist.get(i+1).charAt(nextLen-3);

            if(prevColor != nextColor && (presStation.equals("Ameerpet R10")||presStation.equals("MG Bus Station R19")||presStation.equals("JBS Parade Ground B35"))){
                change.add(presStation);
            }
        }

        Myadapter myadpt=new Myadapter(this,shortestPathlist,change);
        pathList.setAdapter(myadpt);

        TextView changeNo=(TextView) findViewById(R.id.changeNo);
        changeNo.setText(Integer.toString(change.size()));

        TextView stationNo=(TextView) findViewById(R.id.stationNo);
        stationNo.setText(Integer.toString(shortestPathlist.size()));


        InputStream is= getResources().openRawResource(R.raw.fare);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String priceLine="";

        try {
            int count=0;
            while(true){
                try {
                    if ((priceLine = reader.readLine())==null) break;
                    else{
                        if(count==src) {
                            break;
                        }
                        count++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            return;
        }

        TextView timeNo=findViewById(R.id.ttimeNo);
        timeNo.setText((Math.round(shortestPathlist.size()*2.203)+(6*change.size()))+"");

        TextView fareNo=(TextView) findViewById(R.id.fareNo);
        String[] fare=priceLine.split(",");
        fareNo.setText(fare[dest]);

        int countChange=change.size();
        if(countChange==1){
            CardView view1=findViewById(R.id.changeCard2);

            view1.setVisibility(View.GONE);
        }else if(countChange==0){
            CardView view1=findViewById(R.id.changeCard1);
            view1.setVisibility(View.GONE);

            view1=findViewById(R.id.changeCard2);
            view1.setVisibility(View.GONE);
        }

        TextView set=(TextView) findViewById(R.id.pf1);

        for(int i=1;i<shortestPathlist.size()-1;i++){
            if(countChange<1)break;
            if(change.contains(shortestPathlist.get(i))){

                String prev=shortestPathlist.get(i-1);
                int prevLen=prev.length();

                String next=shortestPathlist.get(i+1);
                int nextLen=next.length();

                char prevCol=prev.charAt(prevLen-3);
                char nextCol=next.charAt(nextLen-3);

                if(prevCol!=nextCol){
                    if(prevCol=='R' && nextCol=='B') {
                        if (next.equals("Begumpet B39")) {
                            set.setText("Nagole");
                        }else{
                            set.setText("Raidurg");
                        }
                    } else if (prevCol == 'R') {
                        set.setText("JBS Parade Ground");
                    }
                    else if(prevCol=='G' && nextCol=='R'){
                        if(next.equals("Malakpet R20")) {
                            set.setText("L B Nagar");
                        }else{
                            set.setText("Miyapur");
                        }
                    }
                    else if(prevCol=='G' && nextCol=='B'){
                        if(next.equals("Secunderabad East B34")) {
                            set.setText("Nagole");
                        }else{
                            set.setText("Raidurg");
                        }
                    }
                    else if(prevCol=='B' && nextCol=='G'){
                        set.setText("MG Bus Station");
                    }
                    else if(prevCol=='B' && nextCol=='R'){
                        if(next.equals("Punjagutta R11")) {
                            set.setText("L B Nagar");
                        }else{
                            set.setText("Miyapur");
                        }
                    }
                    set=(TextView) findViewById(R.id.pf2);
                    countChange--;
                }

            }
        }

    }

    class Graph {
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        HashMap<Integer, String> nodeName = new HashMap<>();

        Graph() {

            nodeName.put(0, "Miyapur R00");
            nodeName.put(1, "JNTU College R01");
            nodeName.put(2, "KPHB Colony R02");
            nodeName.put(3, "Kukatpally R03");
            nodeName.put(4, "Balanagar R04");
            nodeName.put(5, "Moosapet R05");
            nodeName.put(6, "Bharatnagar R06");
            nodeName.put(7, "Erragadda R07");
            nodeName.put(8, "ESI Hospital R08");
            nodeName.put(9, "SR Nagar R09");
            nodeName.put(10, "Ameerpet R10");
            nodeName.put(11, "Punjagutta R11");
            nodeName.put(12, "Irrum Manzil R12");
            nodeName.put(13, "Khairatabad R13");
            nodeName.put(14, "Lakdi Ka Pul R14");
            nodeName.put(15, "Assembly R15");
            nodeName.put(16, "Nampally R16");
            nodeName.put(17, "Gandhi Bhavan R17");
            nodeName.put(18, "Osmania Medical College R18");
            nodeName.put(19, "MG Bus Station R19");
            nodeName.put(20, "Malakpet R20");
            nodeName.put(21, "New Market R21");
            nodeName.put(22, "Musarambagh R22");
            nodeName.put(23, "Dilsukhnagar R23");
            nodeName.put(24, "Chaitanyapuri R24");
            nodeName.put(25, "Victoria Memorial R25");
            nodeName.put(26, "LB Nagar R26");

            nodeName.put(27, "Nagole B27");
            nodeName.put(28, "Uppal B28");
            nodeName.put(29, "Stadium B29");
            nodeName.put(30, "NGRI B30");
            nodeName.put(31, "Habsiguda B31");
            nodeName.put(32, "Tarnaka B32");
            nodeName.put(33, "Mettuguda B33");
            nodeName.put(34, "Secunderabad East B34");
            nodeName.put(35, "JBS Parade Ground B35");
            nodeName.put(36, "Paradise B36");
            nodeName.put(37, "Rasoolpura B37");
            nodeName.put(38, "Prakash Nagar B38");
            nodeName.put(39, "Begumpet B39");
            nodeName.put(40, "Madhura Nagar B40");
            nodeName.put(41, "Yusufguda B41");
            nodeName.put(42, "Jubilee Hills Road No 5  B42");
            nodeName.put(43, "Jubilee Hills Check Post B43");
            nodeName.put(44, "Peddamma Gudi B44");
            nodeName.put(45, "Madhapur B45");
            nodeName.put(46, "Durgam Cheruvu B46");
            nodeName.put(47, "Hitec City B47");
            nodeName.put(48, "Raidug B48");

            nodeName.put(49, "Secunderabad West G49");
            nodeName.put(50, "Gandhi Hospital G50");
            nodeName.put(51, "Musheerabad G51");
            nodeName.put(52, "RTC X Roads G52");
            nodeName.put(53, "Chikkadpally G53");
            nodeName.put(54, "Narayanaguda G54");
            nodeName.put(55, "Sultan Bazaar G55");
        }
        public void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public void buildGraph() {


            for (int i = 0; i < 56; i++) {
                adj.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < 26; i++) {
                addEdge(adj, i, i + 1);
            }

            for (int i = 27; i <= 38; i++) {
                addEdge(adj, i, i + 1);
            }

            addEdge(adj, 39, 10);
            addEdge(adj, 10, 40);

            for (int i = 40; i < 48; i++) {
                addEdge(adj, i, i + 1);
            }

            for (int i = 49; i < 55; i++) {
                addEdge(adj, i, i + 1);
            }

            addEdge(adj, 55, 19);
            addEdge(adj, 35, 49);
        }


        public ArrayList<String> shortestPath(int src, int dest) {

            int visited[] = new int[56];
            int parent[] = new int[56];


            Queue<Integer> q = new LinkedList<>();
            parent[src] = -1;
            q.add(src);

            while (!q.isEmpty()) {
                int node = q.peek();
                q.remove();
                for (int it : adj.get(node)) {
                    if (visited[it] == 0) {
                        visited[it] = 1;
                        parent[it] = node;
                        q.add(it);
                    }
                }
            }

            int curr = dest;
            ArrayList<String> res = new ArrayList<>();
            res.add(nodeName.get(curr));

            while (curr != src) {
                System.out.println();
                res.add(nodeName.get(parent[curr]));
                curr = parent[curr];
            }

            Collections.reverse(res);
            return res;
        }
    }
}