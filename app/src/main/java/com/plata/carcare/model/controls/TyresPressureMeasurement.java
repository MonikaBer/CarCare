package com.plata.carcare.model.controls;

import com.plata.carcare.model.Control;

import java.util.Date;

public class TyresPressureMeasurement extends Control {

    private float tyresPressureValue;  // in the all tyres in PSI unit

    public TyresPressureMeasurement(Status status, Date date, int mileage, String desc, int cycle, float tyresPressureValue) {
        super(status, date, mileage, desc, cycle);
        this.tyresPressureValue = tyresPressureValue;
    }

    public float getTyresPressureValue() {
        return tyresPressureValue;
    }

    public void setTyresPressureValue(float tyresPressureValue) {
        this.tyresPressureValue = tyresPressureValue;
    }
}
