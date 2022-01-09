package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class CoolantChange extends Repair {

    private enum Destiny { WINTER, SUMMER }

    private String coolantProducent;
    private String coolantType;
    private Destiny coolantDestiny;
    private float coolantAmount;  // in litres

    public CoolantChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String coolantProducent, String coolantType, Destiny coolantDestiny, float coolantAmount) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.coolantProducent = coolantProducent;
        this.coolantType = coolantType;
        this.coolantDestiny = coolantDestiny;
        this.coolantAmount = coolantAmount;
    }

    public String getCoolantProducent() {
        return coolantProducent;
    }

    public void setCoolantProducent(String coolantProducent) {
        this.coolantProducent = coolantProducent;
    }

    public String getCoolantType() {
        return coolantType;
    }

    public void setCoolantType(String coolantType) {
        this.coolantType = coolantType;
    }

    public Destiny getCoolantDestiny() {
        return coolantDestiny;
    }

    public void setCoolantDestiny(Destiny coolantDestiny) {
        this.coolantDestiny = coolantDestiny;
    }

    public float getCoolantAmount() {
        return coolantAmount;
    }

    public void setCoolantAmount(float coolantAmount) {
        this.coolantAmount = coolantAmount;
    }
}
