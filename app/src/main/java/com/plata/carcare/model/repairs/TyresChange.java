package com.plata.carcare.model.repairs;

import com.plata.carcare.model.Repair;

import java.util.Date;

public class TyresChange extends Repair {

    private enum Destiny { WINTER, SUMMER, MULTISEASON }

    private String tyresProducent;
    private String tyresType;
    private int tyresWidth;
    private int tyresProfile;
    private int tyresRimSize;
    private int tyresLoadIndex;
    private char tyresSpeedRating;
    private int tyresProductionYear;
    private int tyresProductionWeek;

    public TyresChange(Status status, Date date, int mileage, String desc, int cycle, float partsCost, float workCost, String tyresProducent, String tyresType, int tyresWidth, int tyresProfile, int tyresRimSize, int tyresLoadIndex, char tyresSpeedRating, int tyresProductionYear, int tyresProductionWeek) {
        super(status, date, mileage, desc, cycle, partsCost, workCost);
        this.tyresProducent = tyresProducent;
        this.tyresType = tyresType;
        this.tyresWidth = tyresWidth;
        this.tyresProfile = tyresProfile;
        this.tyresRimSize = tyresRimSize;
        this.tyresLoadIndex = tyresLoadIndex;
        this.tyresSpeedRating = tyresSpeedRating;
        this.tyresProductionYear = tyresProductionYear;
        this.tyresProductionWeek = tyresProductionWeek;
    }

    public String getTyresProducent() {
        return tyresProducent;
    }

    public void setTyresProducent(String tyresProducent) {
        this.tyresProducent = tyresProducent;
    }

    public String getTyresType() {
        return tyresType;
    }

    public void setTyresType(String tyresType) {
        this.tyresType = tyresType;
    }

    public int getTyresWidth() {
        return tyresWidth;
    }

    public void setTyresWidth(int tyresWidth) {
        this.tyresWidth = tyresWidth;
    }

    public int getTyresProfile() {
        return tyresProfile;
    }

    public void setTyresProfile(int tyresProfile) {
        this.tyresProfile = tyresProfile;
    }

    public int getTyresRimSize() {
        return tyresRimSize;
    }

    public void setTyresRimSize(int tyresRimSize) {
        this.tyresRimSize = tyresRimSize;
    }

    public int getTyresLoadIndex() {
        return tyresLoadIndex;
    }

    public void setTyresLoadIndex(int tyresLoadIndex) {
        this.tyresLoadIndex = tyresLoadIndex;
    }

    public char getTyresSpeedRating() {
        return tyresSpeedRating;
    }

    public void setTyresSpeedRating(char tyresSpeedRating) {
        this.tyresSpeedRating = tyresSpeedRating;
    }

    public int getTyresProductionYear() {
        return tyresProductionYear;
    }

    public void setTyresProductionYear(int tyresProductionYear) {
        this.tyresProductionYear = tyresProductionYear;
    }

    public int getTyresProductionWeek() {
        return tyresProductionWeek;
    }

    public void setTyresProductionWeek(int tyresProductionWeek) {
        this.tyresProductionWeek = tyresProductionWeek;
    }
}
