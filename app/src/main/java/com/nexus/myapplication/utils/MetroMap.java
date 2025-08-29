package com.nexus.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.nexus.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MetroMap{
    public HashMap<MetroStation, ArrayList<MetroStation>> map;
    public String[] Stations;

    MetroMap(){
        map = new HashMap<MetroStation, ArrayList<MetroStation>>();

        Stations = new String[Const.TOTAL_STATIONS];
    }

    public void buildMap(Context context) {
        try {
            // Read JSON from res/raw/metrolines.json
            InputStream is = context.getResources().openRawResource(R.raw.metrolines);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            String jsonString = builder.toString();

            // Parse JSON
            JSONObject root = new JSONObject(jsonString);
            JSONArray linesArray = root.getJSONArray("lines");

            List<MetroLine> metroLines = new ArrayList<>();
            HashMap<Integer, MetroStation> hs = new HashMap<>();

            for (int i = 0; i < linesArray.length(); i++) {
                JSONObject lineObj = linesArray.getJSONObject(i);
                MetroLine metroLine = new MetroLine();
                metroLine.lineName = lineObj.getString("lineName");
                metroLine.lineColor = lineObj.optString("lineColor", "#000000");

                JSONArray stationsArray = lineObj.getJSONArray("stations");
                metroLine.stations = new ArrayList<>();

                for (int j = 0; j < stationsArray.length(); j++) {
                    JSONObject stationObj = stationsArray.getJSONObject(j);
                    MetroStation station;
                    if(!hs.containsKey(stationObj.getInt("stationId"))) {
                        station= new MetroStation();
                        station.stationId = stationObj.getInt("stationId");
                        station.name = stationObj.getString("name");
                        station.latitude = stationObj.getString("latitude");
                        station.longitude = stationObj.getString("longitude");
                        station.isInterchange = stationObj.getBoolean("isInterchange");
                        hs.put(station.stationId, station);
                    }else{
                        station = hs.get(stationObj.getInt("stationId"));
                    }
                    metroLine.stations.add(station);
                }

                metroLines.add(metroLine);
            }

            for(MetroLine metroline : metroLines){

                for(MetroStation metrostation : metroline.stations){

                    for(int index=0;index<metroline.stations.size()-1;index++){

                             // from a->b
                            if(map.containsKey(metroline.stations.get(index))) {
                                map.get(metroline.stations.get(index)).add(metroline.stations.get(index + 1));
                                map.compute(metroline.stations.get(index), (k, ls) -> ls);
                            }else{
                                ArrayList<MetroStation> list = new ArrayList<>();
                                list.add(metroline.stations.get(index+1));
                                map.put(metroline.stations.get(index), list);
                            }

                            //from b->a
                            if(map.containsKey(metroline.stations.get(index+1))) {
                                map.get(metroline.stations.get(index + 1)).add(metroline.stations.get(index));
                                map.compute(metroline.stations.get(index+1), (k, ls) -> ls);
                            }else{
                                ArrayList<MetroStation> list = new ArrayList<>();
                                list.add(metroline.stations.get(index));
                                map.put(metroline.stations.get(index+1), list);
                            }
                    }

                }

            }

            //find shortest path in the map



            // ✅ Use metroLines as needed
            Log.d("MetroMap", "Loaded " + metroLines.size() + " lines");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MetroMap", "Error loading metro lines: " + e.getMessage());
        }
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