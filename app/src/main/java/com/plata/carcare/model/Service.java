package com.plata.carcare.model;

import java.util.Date;

public class Service {

    public enum Status { PLANNED, DONE }

    protected int id;
    protected Status status;
    protected Date date;
    protected String name;
    protected int mileage;  // in km
    protected String desc;
    protected String type;

    public Service(int id, Status status, Date date, String name, int mileage, String desc, String type) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.name = name;
        this.mileage = mileage;
        this.desc = desc;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
