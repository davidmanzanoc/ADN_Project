package com.example.infrastructure.repository;

import android.content.Context;

import com.example.domain.parking.exception.GlobalException;
import com.example.domain.vehicle.car.model.Car;
import com.example.domain.vehicle.car.repository.CarRepository;
import com.example.infrastructure.database.ParkingDatabase;
import com.example.infrastructure.database.entity.CarEntity;
import com.example.infrastructure.threads.car.GetCarsThread;
import com.example.infrastructure.threads.car.GetNumberOfCarsThread;
import com.example.infrastructure.translate.CarTranslate;

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
        ParkingDatabase.EXECUTOR_SERVICE.execute(() -> parkingDatabase.carDao().saveCar(carEntity));
    }

    @Override
    public void deleteCar(Car car) {
        CarEntity carEntity = CarTranslate.translateCarFromDomainToDB(car);
        ParkingDatabase.EXECUTOR_SERVICE.execute(() ->
                parkingDatabase.carDao().deleteCar(carEntity.getLicensePlate()));
    }

    @Override
    public int getNumberOfCars() {
        int numberOfCars;
        GetNumberOfCarsThread getNumberOfCarsThread = new GetNumberOfCarsThread(parkingDatabase);
        try {
            numberOfCars = getNumberOfCarsThread.execute().get();
        } catch (Exception e) {
            throw new GlobalException("Error al obtener el numero de carros", e);
        }
        return numberOfCars;
    }

    @Override
    public List<Car> getCars() {
        List<Car> carList = new ArrayList<>();
        GetCarsThread getCarsThread = new GetCarsThread(parkingDatabase);
        try {
            List<CarEntity> carEntityList = getCarsThread.execute().get();
            carList.addAll(CarTranslate.translateCarListFromDBToDomain(carEntityList));
        } catch (Exception e) {
            throw new GlobalException("Error al obtener la lista de carros", e);
        }
        return carList;
    }
}
