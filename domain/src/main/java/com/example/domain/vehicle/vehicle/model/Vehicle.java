package com.example.domain.vehicle.vehicle.model;

import com.example.domain.parking.exception.GlobalException;
import com.example.domain.parking.service.ParkingService;

import java.time.LocalDateTime;

public abstract class Vehicle {

    protected String licensePlate;
    protected LocalDateTime entryDate;

    public Vehicle(String licensePlate, LocalDateTime entryDate) {
        setLicensePlate(licensePlate);
        setEntryDate(entryDate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    private void setLicensePlate(String licensePlate) {
        if (!"".equals(licensePlate))
            this.licensePlate = licensePlate;
        else
            throw new GlobalException("Ingrese la placa del vehiculo", new Exception());
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    private void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public abstract void saveVehicle(ParkingService parkingService);

    public abstract int parkingCost(ParkingService parkingService);
}
