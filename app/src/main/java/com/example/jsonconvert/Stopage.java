package com.example.jsonconvert;

import java.util.List;

public class Stopage {
    int id;
    String stationName;
    int order;
    float distanceFromUttara;
    String busList;


    public Stopage() {
    }

    public Stopage(int id, String stationName, int order, float distanceFromUttara, String busList) {
        this.id = id;
        this.stationName = stationName;
        this.order = order;
        this.distanceFromUttara = distanceFromUttara;
        this.busList = busList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public float getDistanceFromUttara() {
        return distanceFromUttara;
    }

    public void setDistanceFromUttara(float distanceFromUttara) {
        this.distanceFromUttara = distanceFromUttara;
    }

    public String getBusList() {
        return busList;
    }

    public void setBusList(String busList) {
        this.busList = busList;
    }
}
