package com.example.domain.model;

import java.time.LocalDateTime;

public class Vehicle {

    protected String licensePlate;
    protected LocalDateTime entryDate;
    private static final Rate RATE = new Rate(0, 0, 0);

    public Vehicle(String licensePlate, LocalDateTime entryDate) {
        setLicensePlate(licensePlate);
        setEntryDate(entryDate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    private void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    private void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Rate getRATE() {
        return RATE;
    }
}
