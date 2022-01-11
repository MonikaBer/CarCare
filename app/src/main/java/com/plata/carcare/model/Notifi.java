package com.plata.carcare.model;

import java.util.Date;

public class Notifi {

    private Date date;
    private String name;
    private Service service;
    private String desc;

    public Notifi(Date date, String name, Service service, String desc) {
        this.date = date;
        this.name = name;
        this.service = service;
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
