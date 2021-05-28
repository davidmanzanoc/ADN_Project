package com.example.infrastructure.translate;

import com.example.domain.vehicle.car.model.Car;
import com.example.infrastructure.database.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarTranslate {

    private CarTranslate() {}

    public static CarEntity translateCarFromDomainToDB(Car car) {
        CarEntity carEntity = new CarEntity();
        carEntity.setLicensePlate(car.getLicensePlate());
        carEntity.setEntryDate(car.getEntryDate().toString());
        return carEntity;
    }

    public static Car translateCarFromDBToDomain(CarEntity carEntity) {
        LocalDateTime localDateTime = LocalDateTime.parse(carEntity.getEntryDate());
        return new Car(carEntity.getLicensePlate(), localDateTime);
    }

    public static List<Car> translateCarListFromDBToDomain(List<CarEntity> carList) {
        List<Car> translatedCarList = new ArrayList<>();
        carList.forEach(car -> translatedCarList.add(translateCarFromDBToDomain(car)));
        return translatedCarList;
    }
}
