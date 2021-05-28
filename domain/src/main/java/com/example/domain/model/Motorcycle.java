package com.example.domain.model;

import java.time.LocalDateTime;

public class Motorcycle extends Vehicle {

    private int cylinderCapacity;
    private static final Rate RATE = new Rate(500, 4000, 2000);

    public Motorcycle(String licensePlate, LocalDateTime entryDate, int cylinderCapacity) {
        setLicensePlate(licensePlate);
        setEntryDate(entryDate);
        setCylinderCapacity(cylinderCapacity);
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    private void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    protected void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    @Override
    protected void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public Rate getRATE() {
        return RATE;
    }
}
