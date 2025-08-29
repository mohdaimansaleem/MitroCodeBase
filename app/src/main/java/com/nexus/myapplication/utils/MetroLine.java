package com.nexus.myapplication.utils;

import java.util.ArrayList;
import java.util.List;

public class MetroLine {
    String lineName;
    String lineColor; // Optional
    List<MetroStation> stations;

    public  MetroLine(){}
    public MetroLine(String lineName) {
        this.lineName = lineName;
        this.stations = new ArrayList<>();
    }

    public MetroLine(String lineName, String lineColor) {
        this.lineName = lineName;
        this.lineColor = lineColor;
        this.stations = new ArrayList<>();
    }

    public void addMetroStation(MetroStation station) {
        this.stations.add(station);
    }

    public void addMetroStationsList(List<MetroStation> stationsList) {
        this.stations.addAll(stationsList);
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineColor() { return lineColor; }


    @Override
    public String toString() {
        return "MetroLine{" +
                "lineName='" + lineName + '\'' +
                (lineColor != null ? ", lineColor='" + lineColor + '\'' : "") +
                ", stationsCount=" + (stations != null ? stations.size() : 0) +
                '}';
    }
}