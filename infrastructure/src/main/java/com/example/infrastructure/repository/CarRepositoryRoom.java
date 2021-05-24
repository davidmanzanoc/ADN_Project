package com.example.infrastructure.repository;

import android.content.Context;

import com.example.domain.model.Car;
import com.example.domain.repository.CarRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.CarEntity;
import com.example.infrastructure.translate.CarTranslate;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class CarRepositoryRoom implements CarRepository {

    private ParkingDatabase parkingDatabase;

    @Inject
    public CarRepositoryRoom(@ApplicationContext Context context) {
        parkingDatabase = ParkingDatabase.getInstance(context);
    }

    @Override
    public void saveCar(Car car) {
        CarEntity carEntity = CarTranslate.translateCarFromDomainToDB(car);
        parkingDatabase.carDao().saveCar(carEntity);
    }

    @Override
    public void deleteCar(Car car) {
        CarEntity carEntity = CarTranslate.translateCarFromDomainToDB(car);
        parkingDatabase.carDao().deleteCar(carEntity.licensePlate);
    }

    @Override
    public int getNumberOfCars() {
        return 0;
    }

    @Override
    public List<Car> getCars() {
        return null;
    }
}
