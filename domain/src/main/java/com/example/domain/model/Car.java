package com.example.domain.model;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    private static final Rate RATE = new Rate(1000, 8000, 0);

    public Car(String licensePlate, LocalDateTime entryDate) {
        setLicensePlate(licensePlate);
        setEntryDate(entryDate);
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
