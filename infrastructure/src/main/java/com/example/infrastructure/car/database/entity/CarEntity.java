package com.example.infrastructure.car.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class CarEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String licensePlate;
    private String entryDate;

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public int getId() {
        return id;
    }
}
