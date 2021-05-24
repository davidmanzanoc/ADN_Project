package com.example.domain.service;

import androidx.lifecycle.MutableLiveData;

import com.example.domain.exception.ParkingLimitException;
import com.example.domain.exception.RestrictedAccessByDayException;
import com.example.domain.model.Car;
import com.example.domain.model.Motorcycle;
import com.example.domain.model.Rate;
import com.example.domain.model.Vehicle;
import com.example.domain.repository.CarRepository;
import com.example.domain.repository.MotorcycleRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ParkingService {

    private CarRepository carRepository;
    private MotorcycleRepository motorcycleRepository;
    private final String FIRST_LETTER_LICENSE_PLATE = "A";
    private final int SUNDAY = 7;
    private final int MONDAY = 1;
    public static final int MAX_NUMBER_OF_CARS = 20;
    public static int MAX_NUMBER_OF_MOTORCYCLES = 10;
    private final float MILLISECONDS_IN_AN_HOUR = 3600000;
    private final int HOURS_IN_A_DAY = 24;
    private final int HOUR_LIMIT = 9;
    private final int CYLINDER_CAPACITY_LIMIT = 500;

    @Inject
    public ParkingService(CarRepository carRepository, MotorcycleRepository motorcycleRepository) {
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public void saveCar(Car car, int currentDay) {
        int numberOfCars = carRepository.getNumberOfCars();
        if (numberOfCars == MAX_NUMBER_OF_CARS) {
            throw new ParkingLimitException();
        } else if (validateLicensePlate(car.getLicensePlate(), currentDay)) {
            throw new RestrictedAccessByDayException();
        } else {
            carRepository.saveCar(car);
        }
    }

    public void saveMotorcycle(Motorcycle motorcycle, int currentDay) {
        int numberOfMotorcycles = motorcycleRepository.getNumberOfMotorcycles();
        if (numberOfMotorcycles == MAX_NUMBER_OF_MOTORCYCLES) {
            throw new ParkingLimitException();
        } else if (validateLicensePlate(motorcycle.getLicensePlate(), currentDay)) {
            throw new RestrictedAccessByDayException();
        } else {
            motorcycleRepository.saveMotorcycle(motorcycle);
        }
    }

    public boolean validateLicensePlate(String licensePlate, int currentDay) {
        return (licensePlate.startsWith(FIRST_LETTER_LICENSE_PLATE) && (currentDay == SUNDAY || currentDay == MONDAY));
    }

    public void deleteCar(Car car) {
        carRepository.deleteCar(car);
    }

    public void deleteMotorcycle(Motorcycle motorcycle) {
        motorcycleRepository.deleteMotorcycle(motorcycle);
    }

    public MutableLiveData<List<Vehicle>> getVehicles() {
        MutableLiveData<List<Vehicle>> listMutableLiveData = new MutableLiveData<>();
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.addAll(carRepository.getCars());
        vehicleList.addAll(motorcycleRepository.getMotorcycles());
        listMutableLiveData.setValue(vehicleList);
        return listMutableLiveData;
    }

    public int carParkingCost(Car car, LocalDateTime exitDate) {
        return calculateParkingCost(car, exitDate);
    }

    public int motorcycleParkingCost(Motorcycle motorcycle, LocalDateTime exitDate) {
        int parkingCost = calculateParkingCost(motorcycle, exitDate);
        if (motorcycle.getCylinderCapacity() > CYLINDER_CAPACITY_LIMIT) {
            parkingCost += motorcycle.getRATE().getSurplus();
        }
        return parkingCost;
    }

    public int calculateParkingCost(Vehicle vehicle, LocalDateTime exitDate) {
        Rate vehicleRate = vehicle.getRATE();
        int priceHour = vehicleRate.getPriceHour();
        int priceDay = vehicleRate.getPriceDay();
        int parkingCost;
        int parkingTime = getParkingTime(vehicle.getEntryDate(), exitDate);
        if (parkingTime < HOUR_LIMIT) {
            parkingCost = parkingTime * priceHour;
        } else {
            int days = parkingTime / HOURS_IN_A_DAY;
            int hours = parkingTime % HOURS_IN_A_DAY;
            if (hours >= HOUR_LIMIT) {
                days += 1;
                parkingCost = days * priceDay;
            } else {
                parkingCost = (days * priceDay) + (hours * priceHour);
            }
        }
        return parkingCost;
    }

    public int getParkingTime(LocalDateTime entryDate, LocalDateTime exitDate) {
        long timeElapsed = entryDate.until(exitDate, ChronoUnit.MILLIS);
        return (int) Math.ceil(timeElapsed / MILLISECONDS_IN_AN_HOUR);
    }

}
