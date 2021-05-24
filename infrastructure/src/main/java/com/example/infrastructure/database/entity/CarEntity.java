package com.example.infrastructure.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class CarEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String licensePlate;
    public String entryDate;

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }
}
