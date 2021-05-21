package com.example.domain.model;

import java.time.LocalDateTime;

public class Motorcycle extends Vehicle {

    private int cylinderCapacity;
    private final Rate rate = new Rate(500, 4000);
    private final int MAX_QUANTITY = 10;

    public Motorcycle(String licensePlate, LocalDateTime entryDate, int cylinderCapacity) {
        super(licensePlate, entryDate);
        this.cylinderCapacity = cylinderCapacity;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(int cylinderCapacity) {
        if (cylinderCapacity <= 0) {
            //Exception
        } else {
            this.cylinderCapacity = cylinderCapacity;
        }
    }

    public Rate getRate() {
        return rate;
    }

    public int getMAX_QUANTITY() {
        return MAX_QUANTITY;
    }
}
