package com.plata.carcare.model;

import java.util.Date;

public class Control extends Service {

    public Control(Status status, Date date, int mileage, String desc, int cycle) {
        super(status, date, mileage, desc, cycle);
    }
}
