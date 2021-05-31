package com.example.infrastructure.motorcycle.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "motorcycle")
public class MotorcycleEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String licensePlate;
    private String entryDate;
    private int cylinderCapacity;

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }
}
