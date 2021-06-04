package com.example.infrastructure.car.respository;

import android.content.Context;

import com.example.domain.parking.exception.GlobalException;
import com.example.domain.vehicle.car.model.Car;
import com.example.domain.vehicle.car.repository.CarRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.car.database.entity.CarEntity;
import com.example.infrastructure.car.translate.CarTranslate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class CarRepositoryRoom implements CarRepository {

    private final ParkingDatabase parkingDatabase;

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
        parkingDatabase.carDao().deleteCar(carEntity.getLicensePlate());
    }

    @Override
    public int getNumberOfCars() {
        int numberOfCars;
        try {
            numberOfCars = parkingDatabase.carDao().getNumberOfCars();
        } catch (Exception e) {
            throw new GlobalException("Error al obtener el numero de carros", e);
        }
        return numberOfCars;
    }

    @Override
    public List<Car> getCars() {
        List<Car> carList = new ArrayList<>();
        try {
            List<CarEntity> carEntityList = parkingDatabase.carDao().getCars();
            carList.addAll(CarTranslate.translateCarListFromDBToDomain(carEntityList));
        } catch (Exception e) {
            throw new GlobalException("Error al obtener la lista de carros", e);
        }
        return carList;
    }
}
