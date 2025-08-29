package com.nexus.myapplication.utils;

import java.util.ArrayList;

public class Metro {
    ArrayList<MetroLine> metroLines;

    Metro(){
        metroLines = new ArrayList<>();
    }

    void addMetroLine(MetroLine metroLine){
        metroLines.add(metroLine);
    }
}


