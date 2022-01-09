package com.plata.carcare.model;

public class Expense {

    private int mileage;
    private float cost;
    private String desc;

    public Expense(int mileage, float cost, String desc) {
        this.mileage = mileage;
        this.cost = cost;
        this.desc = desc;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
