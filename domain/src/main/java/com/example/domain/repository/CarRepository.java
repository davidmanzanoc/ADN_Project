package com.example.domain.repository;

import com.example.domain.model.Car;

import java.util.List;

public interface CarRepository {

    void saveCar(Car car);

    void deleteCar(Car car);

    int getNumberOfCars();

    List<Car> getCars();

}
