package com.example.domain.model;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    private final Rate RATE = new Rate(1000, 8000, 0);

    public Car(String licensePlate, LocalDateTime entryDate) {
        super(licensePlate, entryDate);
    }

    @Override
    public Rate getRATE() {
        return RATE;
    }
}
