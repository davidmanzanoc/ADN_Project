package com.example.domain.vehicle.motorcycle.model;

import com.example.domain.parking.exception.GlobalException;
import com.example.domain.parking.service.ParkingService;
import com.example.domain.vehicle.vehicle.model.Vehicle;

import java.time.LocalDateTime;

public class Motorcycle extends Vehicle {

    private int cylinderCapacity;

    public Motorcycle(String licensePlate, LocalDateTime entryDate, String cylinderCapacity) {
        super(licensePlate, entryDate);
        setCylinderCapacity(cylinderCapacity);
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    private void setCylinderCapacity(String cylinderCapacity) {
        if (!"".equals(cylinderCapacity))
            this.cylinderCapacity = Integer.parseInt(cylinderCapacity);
        else
            throw new GlobalException("Ingrese el cilindraje del vehiculo", new Exception());
    }

    @Override
    public void saveVehicle(ParkingService parkingService) {
        parkingService.saveMotorcycle(this, getEntryDate().getDayOfWeek().getValue());
    }

    @Override
    public int parkingCost(ParkingService parkingService) {
        int bill = parkingService.motorcycleParkingCost(this, LocalDateTime.now());
        parkingService.deleteMotorcycle(this);
        return bill;
    }
}
