package com.plata.carcare.model;

import java.util.Date;

public class Service {

    protected enum Status { PLANNED, DONE }

    protected Status status;
    protected Date date;
    protected int mileage;  // in km
    protected String desc;

    public Service(Status status, Date date, int mileage, String desc) {
        this.status = status;
        this.date = date;
        this.mileage = mileage;
        this.desc = desc;
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
}
