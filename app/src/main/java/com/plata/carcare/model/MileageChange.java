package com.plata.carcare.model;

import java.util.Date;

public class MileageChange extends Repair {

    private enum Type { AIR_FILTER, ENGINE_OIL }

    private Type type;
    private int cycle;  // in km

    public MileageChange(Status status, Date date, int mileage, String desc, String producent, String productType, String productDesc, float partsCost, float workCost, Type type, int cycle) {
        super(status, date, mileage, desc, producent, productType, productDesc, partsCost, workCost);
        this.type = type;
        this.cycle = cycle;
    }
}
