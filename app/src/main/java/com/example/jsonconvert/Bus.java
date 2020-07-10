package com.example.jsonconvert;

import java.util.List;

public class Bus {

    int id;
    String busName;
    // model class


    public Bus() {
    }

    public Bus(int id, String busName) {
        this.id = id;
        this.busName = busName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
}
