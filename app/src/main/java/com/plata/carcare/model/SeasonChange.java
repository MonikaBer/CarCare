package com.plata.carcare.model;

import java.util.Date;

public class SeasonChange extends Repair {

    private enum Type { TYRES, WASHER_FLUID }
    private enum Season { WINTER, SUMMER }

    private Type type;
    private Season season;

    public SeasonChange(Status status, Date date, int mileage, String desc, String producent, String productType, String productDesc, float partsCost, float workCost, Type type, Season season) {
        super(status, date, mileage, desc, producent, productType, productDesc, partsCost, workCost);
        this.type = type;
        this.season = season;
    }
}
