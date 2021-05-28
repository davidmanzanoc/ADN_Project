package com.example.domain.model;

import java.time.LocalDateTime;

public abstract class Vehicle {

    protected String licensePlate;
    protected LocalDateTime entryDate;
    private static final Rate RATE = new Rate(0, 0, 0);

    public abstract String getLicensePlate();

    protected abstract void setLicensePlate(String licensePlate);

    public abstract LocalDateTime getEntryDate();

    protected abstract void setEntryDate(LocalDateTime entryDate);

    public Rate getRATE() {
        return RATE;
    }
}
