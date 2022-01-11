package com.plata.carcare.model;

import java.util.Date;

public class SeasonChange extends Repair {

    private String season;

    public SeasonChange(int id, Status status, Date date, String name, int mileage, String desc, String type, String producent, String productType, String productDesc, float partsCost, float workCost, String season) {
        super(id, status, date, name, mileage, desc, type, producent, productType, productDesc, partsCost, workCost);
        this.season = season;
    }
}
