package com.antailbaxt3r.kachranikaal;

public class Bin {

    private float latitude, longitude;
    private String type;
    private boolean full;


    public Bin() {
    }

    public Bin(float latitude, float longitude, String type, boolean full) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.full = full;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
