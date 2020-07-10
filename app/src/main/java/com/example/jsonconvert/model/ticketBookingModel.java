package com.example.jsonconvert.model;

import java.security.PublicKey;

public class ticketBookingModel {
    public  String from_location_ET;
    public String to_location_ET;
    public String date_ET;
    public String time_ET;
    public String bus_name ;
    public  String uid;


    public String getFrom_location_ET() {
        return from_location_ET;
    }

    public void setFrom_location_ET(String from_location_ET) {
        this.from_location_ET = from_location_ET;
    }

    public ticketBookingModel(String from_location_ET, String to_location_ET, String date_ET, String time_ET, String bus_name, String uid) {
        this.from_location_ET = from_location_ET;
        this.to_location_ET = to_location_ET;
        this.date_ET = date_ET;
        this.time_ET = time_ET;
        this.bus_name = bus_name;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTo_location_ET() {
        return to_location_ET;
    }

    public void setTo_location_ET(String to_location_ET) {
        this.to_location_ET = to_location_ET;
    }

    public String getDate_ET() {
        return date_ET;
    }

    public void setDate_ET(String date_ET) {
        this.date_ET = date_ET;
    }

    public String getTime_ET() {
        return time_ET;
    }

    public void setTime_ET(String time_ET) {
        this.time_ET = time_ET;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public  ticketBookingModel(){

    }




}
