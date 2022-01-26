package com.plata.carcare.model;

import java.util.Date;

public class MileageChange extends Repair {

    private int cycle;  // in km

    public MileageChange(int id, Status status, Date date, String name, int mileage, String desc, String type, float partsCost, int cycle) {
        super(id, status, date, name, mileage, desc, type, partsCost);
        this.cycle = cycle;
    }
}
