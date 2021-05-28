package com.example.domain.vehicle.car.repository;

import com.example.domain.vehicle.car.model.Car;

import java.util.List;

public interface CarRepository {

    void saveCar(Car car);

    void deleteCar(Car car);

    int getNumberOfCars();

    List<Car> getCars();

}
