package com.example.infrastructure.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "motorcycle")
public class MotorcycleEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String licensePlate;
    public String entryDate;
    public int cylinderCapacity;

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }
}
