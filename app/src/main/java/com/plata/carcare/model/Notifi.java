package com.plata.carcare.model;

public class Notifi {

    private int id;
    private String name;
    private String time;  // current mileage / season (WINTER / SUMMER)
    private String type;  // CONTROL / SEASON_CHANGE / MILEAGE_CHANGE

    public Notifi(int id, String name, String time, String type) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
