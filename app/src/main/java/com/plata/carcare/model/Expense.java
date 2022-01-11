package com.plata.carcare.model;

public class Expense {

    private int mileage;
    private String name;
    private float cost;
    private String desc;

    public Expense(int mileage, String name, float cost, String desc) {
        this.mileage = mileage;
        this.name = name;
        this.cost = cost;
        this.desc = desc;
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
