package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class WindshieldWiperFluidChange extends Repair {

    private enum Destiny { WINTER, SUMMER }

    private String windshieldWiperFluidProducent;
    private String windshieldWiperFluidType;
    private Destiny windshieldWiperFluidDestiny;
    private float windshieldWiperFluidAmount;  // in litres

    public WindshieldWiperFluidChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String windshieldWiperFluidProducent, String windshieldWiperFluidType, Destiny windshieldWiperFluidDestiny, float windshieldWiperFluidAmount) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.windshieldWiperFluidProducent = windshieldWiperFluidProducent;
        this.windshieldWiperFluidType = windshieldWiperFluidType;
        this.windshieldWiperFluidDestiny = windshieldWiperFluidDestiny;
        this.windshieldWiperFluidAmount = windshieldWiperFluidAmount;
    }

    public String getWindshieldWiperFluidProducent() {
        return windshieldWiperFluidProducent;
    }

    public void setWindshieldWiperFluidProducent(String windshieldWiperFluidProducent) {
        this.windshieldWiperFluidProducent = windshieldWiperFluidProducent;
    }

    public String getWindshieldWiperFluidType() {
        return windshieldWiperFluidType;
    }

    public void setWindshieldWiperFluidType(String windshieldWiperFluidType) {
        this.windshieldWiperFluidType = windshieldWiperFluidType;
    }

    public Destiny getWindshieldWiperFluidDestiny() {
        return windshieldWiperFluidDestiny;
    }

    public void setWindshieldWiperFluidDestiny(Destiny windshieldWiperFluidDestiny) {
        this.windshieldWiperFluidDestiny = windshieldWiperFluidDestiny;
    }

    public float getWindshieldWiperFluidAmount() {
        return windshieldWiperFluidAmount;
    }

    public void setWindshieldWiperFluidAmount(float windshieldWiperFluidAmount) {
        this.windshieldWiperFluidAmount = windshieldWiperFluidAmount;
    }
}
