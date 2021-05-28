package com.example.domain.vehicle.motorcycle.model;

import com.example.domain.parking.model.Rate;
import com.example.domain.vehicle.vehicle.model.Vehicle;

import java.time.LocalDateTime;

public class Motorcycle extends Vehicle {

    private int cylinderCapacity;
    private static final Rate RATE = new Rate(500, 4000, 2000);

    public Motorcycle(String licensePlate, LocalDateTime entryDate, int cylinderCapacity) {
        super(licensePlate, entryDate);
        setCylinderCapacity(cylinderCapacity);
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    private void setCylinderCapacity(int cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    @Override
    public Rate getRATE() {
        return RATE;
    }
}
