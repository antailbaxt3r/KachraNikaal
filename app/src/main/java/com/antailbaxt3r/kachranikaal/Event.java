package com.antailbaxt3r.kachranikaal;

class Event {

    private String date, id, name, time, venue, img;

    public Event() {
    }

    public Event(String date, String id, String name, String time, String venue, String img) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.time = time;
        this.venue = venue;
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String image) {
        this.img = image;
    }
}

