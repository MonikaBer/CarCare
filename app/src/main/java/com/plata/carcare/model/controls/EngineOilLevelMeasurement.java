package com.plata.carcare.model.controls;

import com.plata.carcare.model.Control;

import java.util.Date;

public class EngineOilLevelMeasurement extends Control {

    private enum Level { MIN, MIDDLE, MAX }

    private Level engineOilLevel;

    public EngineOilLevelMeasurement(Status status, Date date, int mileage, String desc, int cycle, Level engineOilLevel) {
        super(status, date, mileage, desc, cycle);
        this.engineOilLevel = engineOilLevel;
    }

    public Level getEngineOilLevel() {
        return engineOilLevel;
    }

    public void setEngineOilLevel(Level engineOilLevel) {
        this.engineOilLevel = engineOilLevel;
    }
}
