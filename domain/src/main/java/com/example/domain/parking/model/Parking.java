package com.example.domain.parking.model;

public class Parking {

    public Parking() {
    }

    private final Rate carRate = new Rate(1000, 8000, 0);

    private final Rate motorcycleRate = new Rate(500, 4000, 2000);

    public Rate getCarRate() {
        return carRate;
    }

    public Rate getMotorcycleRate() {
        return motorcycleRate;
    }

    public int getHourLimit() {
        return 9;
    }

    public int getCylinderCapacityLimit() {
        return 500;
    }

    public String getFirstLetterLicensePlate() {
        return "A";
    }

    public int getSunday() {
        return 7;
    }

    public int getMonday() {
        return 1;
    }

    public int getMaxNumberOfCars() {
        return 20;
    }

    public int getMaxNumberOfMotorcycles() {
        return 10;
    }
}
