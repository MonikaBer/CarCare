package com.plata.carcare.model;

import java.util.Date;

public class Repair extends Service {

    protected float partsCost;
    protected float workCost;

    public Repair(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost) {
        super(status, date, mileage, desc, cycle);
        this.partsCost = partsCost;
        this.workCost = workCost;
    }

    public float getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(float partsCost) {
        this.partsCost = partsCost;
    }

    public float getWorkCost() {
        return workCost;
    }

    public void setWorkCost(float workCost) {
        this.workCost = workCost;
    }
}
