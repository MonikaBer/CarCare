package com.plata.carcare.model;

import java.util.Date;

public class Repair extends Service {

    protected String producent;
    protected String productType;
    protected String productDesc;
    protected float partsCost;
    protected float workCost;

    public Repair(Status status, Date date, int mileage, String desc, String producent, String productType, String productDesc, float partsCost, float workCost) {
        super(status, date, mileage, desc);
        this.producent = producent;
        this.productType = productType;
        this.productDesc = productDesc;
        this.partsCost = partsCost;
        this.workCost = workCost;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public float getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(float partsCost) {
        this.partsCost = partsCost;
    }

    public float getWorkCost() {
        return workCost;
    }

    public void setWorkCost(float workCost) {
        this.workCost = workCost;
    }
}
