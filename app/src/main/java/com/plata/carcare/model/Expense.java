package com.plata.carcare.model;

import java.util.Date;

public class Expense {

    private int id;
    private Date date;
    private int mileage;
    private String name;
    private double cost;
    private String desc;

    public Expense(int id, Date date, int mileage, String name, double cost, String desc) {
        this.id = id;
        this.date = date;
        this.mileage = mileage;
        this.name = name;
        this.cost = cost;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
