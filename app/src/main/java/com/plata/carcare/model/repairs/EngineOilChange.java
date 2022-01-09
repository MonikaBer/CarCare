package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class EngineOilChange extends Repair {

    private String oilProducent;
    private String oilType;
    private float oilAmount;  // in litres

    private String fuelFilterProducent;
    private String fuelFilterType;

    public EngineOilChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String oilProducent, String oilType, float oilAmount, String fuelFilterProducent, String fuelFilterType) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.oilProducent = oilProducent;
        this.oilType = oilType;
        this.oilAmount = oilAmount;
        this.fuelFilterProducent = fuelFilterProducent;
        this.fuelFilterType = fuelFilterType;
    }

    public String getOilProducent() {
        return oilProducent;
    }

    public void setOilProducent(String oilProducent) {
        this.oilProducent = oilProducent;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public float getOilAmount() {
        return oilAmount;
    }

    public void setOilAmount(float oilAmount) {
        this.oilAmount = oilAmount;
    }

    public String getFuelFilterProducent() {
        return fuelFilterProducent;
    }

    public void setFuelFilterProducent(String fuelFilterProducent) {
        this.fuelFilterProducent = fuelFilterProducent;
    }

    public String getFuelFilterType() {
        return fuelFilterType;
    }

    public void setFuelFilterType(String fuelFilterType) {
        this.fuelFilterType = fuelFilterType;
    }
}
