package com.plata.carcare.model;

import java.util.Date;

public class Repair extends Service {

    protected double partsCost;

    public Repair(int id, Status status, Date date, String name, int mileage, String desc, String type, double partsCost) {
        super(id, status, date, name, mileage, desc, type);
        this.partsCost = partsCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }
}
