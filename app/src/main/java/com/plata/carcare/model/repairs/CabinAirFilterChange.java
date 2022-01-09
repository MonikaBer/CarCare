package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class CabinAirFilterChange extends Repair {

    private String producent;
    private String type;

    public CabinAirFilterChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String producent, String type) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.producent = producent;
        this.type = type;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
