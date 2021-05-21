package com.example.domain.model;
import java.time.LocalDateTime;

public class Vehicle {

    private String licensePlate;
    private LocalDateTime entryDate;

    public Vehicle(String licensePlate, LocalDateTime entryDate) {
        setLicensePlate(licensePlate);
        setEntryDate(entryDate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()){
            //Exception
        } else {
            this.licensePlate = licensePlate;
        }
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        if (entryDate == null){
            //Exception
        } else {
            this.entryDate = entryDate;
        }
    }
}
