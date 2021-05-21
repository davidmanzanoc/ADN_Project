package com.example.domain.model;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    private final Rate RATE = new Rate(1000, 8000);
    private final int MAX_QUANTITY = 20;

    public Car(String licensePlate, LocalDateTime admissionDate) {
        super(licensePlate, admissionDate);
    }

    public Rate getRATE() {
        return RATE;
    }

    public int getMAX_QUANTITY() {
        return MAX_QUANTITY;
    }
}
