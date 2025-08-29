package com.nexus.myapplication.utils;

public class MetroStation {
    int stationId; // This will now come directly from JSON
    String name;
    String latitude;
    String longitude;
    boolean isInterchange;

    public MetroStation() {}
    public MetroStation(int id, String name, String latitude, String longitude, boolean isInterchange) {
        this.stationId = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isInterchange = isInterchange;
    }

    // Getters can be useful
    public int getId() { return stationId; }
    public String getName() { return name; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }


    @Override
    public String toString() {
        return "MetroStation{" +
                "id=" + stationId +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}