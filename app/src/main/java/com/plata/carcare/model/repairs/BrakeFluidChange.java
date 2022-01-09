package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class BrakeFluidChange extends Repair {

    private String brakeFluidProducent;
    private String brakeFluidype;
    private float brakeFluidAmount;  // in litres

    public BrakeFluidChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String brakeFluidProducent, String brakeFluidype, float brakeFluidAmount) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.brakeFluidProducent = brakeFluidProducent;
        this.brakeFluidype = brakeFluidype;
        this.brakeFluidAmount = brakeFluidAmount;
    }

    public String getBrakeFluidProducent() {
        return brakeFluidProducent;
    }

    public void setBrakeFluidProducent(String brakeFluidProducent) {
        this.brakeFluidProducent = brakeFluidProducent;
    }

    public String getBrakeFluidype() {
        return brakeFluidype;
    }

    public void setBrakeFluidype(String brakeFluidype) {
        this.brakeFluidype = brakeFluidype;
    }

    public float getBrakeFluidAmount() {
        return brakeFluidAmount;
    }

    public void setBrakeFluidAmount(float brakeFluidAmount) {
        this.brakeFluidAmount = brakeFluidAmount;
    }
}
