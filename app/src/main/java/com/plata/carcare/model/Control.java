package com.plata.carcare.model;

import java.util.Date;

public class Control extends Service {

    public Control(int id, Status status, Date date, String name, int mileage, String desc, String type) {
        super(id, status, date, name, mileage, desc, type);
    }
}
