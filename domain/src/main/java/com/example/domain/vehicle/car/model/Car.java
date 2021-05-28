package com.example.domain.vehicle.car.model;

import com.example.domain.parking.model.Rate;
import com.example.domain.vehicle.vehicle.model.Vehicle;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    private static final Rate RATE = new Rate(1000, 8000, 0);

    public Car(String licensePlate, LocalDateTime entryDate) {
        super(licensePlate, entryDate);
    }

    @Override
    public Rate getRATE() {
        return RATE;
    }
}
