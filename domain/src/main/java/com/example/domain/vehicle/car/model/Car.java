package com.example.domain.vehicle.car.model;

import com.example.domain.parking.service.ParkingService;
import com.example.domain.vehicle.vehicle.model.Vehicle;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    public Car(String licensePlate, LocalDateTime entryDate) {
        super(licensePlate, entryDate);
    }

    @Override
    public void saveVehicle(ParkingService parkingService) {
        parkingService.saveCar(this, getEntryDate().getDayOfWeek().getValue());
    }

    @Override
    public int parkingCost(ParkingService parkingService) {
        int bill = parkingService.carParkingCost(this, LocalDateTime.now());
        parkingService.deleteCar(this);
        return bill;
    }
}
