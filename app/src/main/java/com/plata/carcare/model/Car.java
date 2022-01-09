package com.plata.carcare.model;

import android.graphics.Bitmap;

public class Car {

    private String name;
    private int mileage;
    private Bitmap photo;
    private String brand;
    private String model;
    private int productionYear;
    private String engineType;

    public Car(String name, int mileage, Bitmap photo, String brand, String model, int productionYear, String engineType) {
        this.name = name;
        this.mileage = mileage;
        this.photo = photo;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.engineType = engineType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}
