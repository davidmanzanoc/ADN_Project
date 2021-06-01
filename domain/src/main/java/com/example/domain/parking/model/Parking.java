package com.example.domain.parking.model;

public class Parking {

    private final Rate carRate;
    private final Rate motorcycleRate;
    private final int hourLimit = 9;
    private final int cylinderCapacity = 500;
    private final String firstLetterLicensePlate = "A";
    private final int sunday = 7;
    private final int monday = 1;
    private final int maxNumberOfCars = 20;
    private final int maxNumberOfMotorcycles = 10;

    public Parking() {
        carRate = new Rate(1000, 8000, 0);
        motorcycleRate = new Rate(500, 4000, 2000);
    }

    public Rate getCarRate() {
        return carRate;
    }

    public Rate getMotorcycleRate() {
        return motorcycleRate;
    }

    public int getHourLimit() {
        return hourLimit;
    }

    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    public String getFirstLetterLicensePlate() {
        return firstLetterLicensePlate;
    }

    public int getSunday() {
        return sunday;
    }

    public int getMonday() {
        return monday;
    }

    public int getMaxNumberOfCars() {
        return maxNumberOfCars;
    }

    public int getMaxNumberOfMotorcycles() {
        return maxNumberOfMotorcycles;
    }
}
