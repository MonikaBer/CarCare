package com.plata.carcare.model;

import java.util.Date;

public class Control extends Service {

    private enum Type { ENGINE_OIL_LEVEL, TYRES_PRESSURE }

    private Type type;

    public Control(Status status, Date date, int mileage, String desc, Type type) {
        super(status, date, mileage, desc);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
