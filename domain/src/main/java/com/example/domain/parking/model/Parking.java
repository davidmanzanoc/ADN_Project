package com.example.domain.parking.model;

public class Parking {

    private final Rate carRate;
    private final Rate motorcycleRate;
    private static final int HOUR_LIMIT = 9;
    private static final int CYLINDER_CAPACITY_LIMIT = 500;
    private static final String FIRST_LETTER_LICENSE_PLATE = "A";
    private static final int SUNDAY = 7;
    private static final int MONDAY = 1;
    private static final int MAX_NUMBER_OF_CARS = 20;
    private static final int MAX_NUMBER_OF_MOTORCYCLES = 10;

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
        return HOUR_LIMIT;
    }

    public int getCylinderCapacityLimit() {
        return CYLINDER_CAPACITY_LIMIT;
    }

    public String getFirstLetterLicensePlate() {
        return FIRST_LETTER_LICENSE_PLATE;
    }

    public int getSunday() {
        return SUNDAY;
    }

    public int getMonday() {
        return MONDAY;
    }

    public int getMaxNumberOfCars() {
        return MAX_NUMBER_OF_CARS;
    }

    public int getMaxNumberOfMotorcycles() {
        return MAX_NUMBER_OF_MOTORCYCLES;
    }
}
