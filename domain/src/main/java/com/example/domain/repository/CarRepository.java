package com.example.domain.repository;

import com.example.domain.model.Car;

public interface CarRepository {

    void saveCar(Car car);

    void deleteCar(Car car);

    int getNumberOfCars();

}
